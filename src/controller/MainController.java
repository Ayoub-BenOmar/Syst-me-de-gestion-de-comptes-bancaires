package controller;

import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import model.compte.Compte;
import model.compte.CompteCourant;
import model.compte.CompteEpargne;
import model.operation.Operation;
import model.operation.Retrait;
import model.operation.Versement;

public class MainController {
    private HashMap<String, Compte> comptes = new HashMap<>();

    public void runApplication(Scanner sc) {
        boolean running = true;

        while(running) {
            try {
                showMainMenu();
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        handleCreateAccountMenu(sc);
                        break;
                    case 2:
                        handleConsultAccountMenu(sc);
                        break;
                    case 3:
                        running = false;
                        System.out.println("==> Au revoir!");
                        break;
                    default:
                        System.out.println("==> Veuillez entrer un choix valide (1-3)");
                }
            } catch (InputMismatchException e) {
                System.out.println("==> Erreur: Veuillez entrer un nombre valide!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("==> Erreur inattendue: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n===> MENU BANQUE <===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Consulter un compte");
        System.out.println("3. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private void handleCreateAccountMenu(Scanner sc) {
        boolean running2 = true;

        while(running2) {
            try {
                showCreateAccountMenu();
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        creeCompteEpargne();
                        break;
                    case 2:
                        creeCompteCourant(sc);
                        break;
                    case 3:
                        running2 = false;
                        break;
                    default:
                        System.out.println("==> Veuillez entrer un choix valide (1-3)");
                }
            } catch (InputMismatchException e) {
                System.out.println("==> Erreur: Veuillez entrer un nombre valide!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("==> Erreur lors de la création du compte: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    private void showCreateAccountMenu() {
        System.out.println("\n===> MENU CRÉER COMPTE <===");
        System.out.println("1. Créer un compte épargne");
        System.out.println("2. Créer un compte courant");
        System.out.println("3. Retour au menu précédent");
        System.out.print("Choisissez une option: ");
    }

    private void handleConsultAccountMenu(Scanner sc) {
        try {
            System.out.print("Entrer votre compte code: ");
            String compteCode = sc.next();
            Compte compteTrouve = trouverCompte(compteCode);

            if (compteTrouve != null) {
                boolean running3 = true;

                while(running3) {
                    try {
                        showConsultAccountMenu();
                        int choice = sc.nextInt();

                        switch(choice) {
                            case 1:
                                handleWithdraw(sc, compteCode);
                                break;
                            case 2:
                                handleDeposit(sc, compteCode);
                                break;
                            case 3:
                                afficherOperations(compteCode);
                                break;
                            case 4:
                                afficherDetailsCompte(compteCode);
                                break;
                            case 5:
                                running3 = false;
                                break;
                            default:
                                System.out.println("==> Veuillez entrer un choix valide (1-5)");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("==> Erreur: Veuillez entrer un nombre valide!");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("==> Erreur lors de l'opération: " + e.getMessage());
                        sc.nextLine();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la recherche du compte: " + e.getMessage());
        }
    }

    private void showConsultAccountMenu() {
        System.out.println("\n===> MENU CONSULTER COMPTE <===");
        System.out.println("1. Retirer");
        System.out.println("2. Faire un dépôt");
        System.out.println("3. Afficher les opérations");
        System.out.println("4. Afficher les détails du compte");
        System.out.println("5. Retour au menu précédent");
        System.out.print("Choisissez une option: ");
    }

    private void handleWithdraw(Scanner sc, String compteCode) {
        try {
            System.out.print("Entrer montant: ");
            double montant = sc.nextDouble();

            if (montant <= 0) {
                System.out.println("==> Le montant doit être positif!");
                return;
            }

            retirer(compteCode, montant);
        } catch (InputMismatchException e) {
            System.out.println("==> Erreur: Veuillez entrer un montant valide!");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("==> Erreur lors du retrait: " + e.getMessage());
        }
    }

    private void handleDeposit(Scanner sc, String compteCode) {
        try {
            System.out.println("1. Faire un versement");
            System.out.println("2. Faire un virement vers un autre compte");
            System.out.print("Choisissez une option: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    try {
                        System.out.print("Entrer le montant à déposer: ");
                        double montant = sc.nextDouble();
                        deposer(compteCode, montant);
                    } catch (InputMismatchException e) {
                        System.out.println("==> Erreur: Veuillez entrer un montant valide!");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Entrer le code du compte destinataire: ");
                        String compteDestinataire = sc.next();
                        System.out.print("Entrer le montant à transférer: ");
                        double montantTransfert = sc.nextDouble();
                        virementVersCompte(compteCode, compteDestinataire, montantTransfert);
                    } catch (InputMismatchException e) {
                        System.out.println("==> Erreur: Veuillez entrer des données valides!");
                        sc.nextLine();
                    }
                    break;
                default:
                    System.out.println("==> Option invalide (1-2)");
            }
        } catch (InputMismatchException e) {
            System.out.println("==> Erreur: Veuillez entrer un choix valide!");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("==> Erreur lors du dépôt: " + e.getMessage());
        }
    }

    public void creeCompteEpargne(){
        try {
            CompteEpargne ce = new CompteEpargne();
            comptes.put(ce.getCode(), ce);
            System.out.println("==> Votre compte épargne a été créé: " + ce.getCode());
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la création du compte épargne: " + e.getMessage());
        }
    }

    public void creeCompteCourant(Scanner sc){
        try {
            CompteCourant cc = new CompteCourant();
            System.out.print("Entrer le montant de découvert: ");
            double decouvert = sc.nextDouble();

            if(decouvert < 0){
                System.out.println("==> Le découvert ne peut pas être négatif! Découvert mis à 0.");
                decouvert = 0;
            }

            cc.setDecouvert(decouvert);
            comptes.put(cc.getCode(), cc);
            System.out.println("==> Votre compte courant a été créé: " + cc.getCode());
            System.out.println("   Découvert autorisé: " + decouvert);
        } catch (InputMismatchException e) {
            System.out.println("==> Erreur: Veuillez entrer un montant valide pour le découvert!");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la création du compte courant: " + e.getMessage());
        }
    }

    public Compte trouverCompte(String code){
        try {
            if (code == null || code.trim().isEmpty()) {
                System.out.println("==> Code de compte invalide!");
                return null;
            }

            Compte c = comptes.get(code);
            if(c == null){
                System.out.println("==> Le compte n'existe pas");
                return null;
            } else {
                System.out.println("==> Bienvenue");
                return c;
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la recherche du compte: " + e.getMessage());
            return null;
        }
    }

    public void retirer(String code, double montant){
        try {
            if (montant <= 0) {
                System.out.println("==> Le montant doit être positif!");
                return;
            }

            Compte c = comptes.get(code);
            if (c != null) {
                c.retirer(montant);
                c.ajouterOperation(new Retrait(montant, "Distributeur ATM"));
            } else {
                System.out.println("==> Compte non trouvé!");
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors du retrait: " + e.getMessage());
        }
    }

    public void deposer(String code, double montant){
        try {
            if (montant <= 0){
                System.out.println("==> Montant invalide!");
                return;
            }

            Compte c = comptes.get(code);
            if (c != null) {
                c.setSolde(c.getSolde() + montant);
                c.ajouterOperation(new Versement(montant, "Salaire"));
                System.out.println("==> Versement de " + montant + " effectué avec succès!");
            } else {
                System.out.println("==> Compte non trouvé!");
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors du versement: " + e.getMessage());
        }
    }

    public void afficherOperations(String compteCode) {
        try {
            Compte c = comptes.get(compteCode);
            if (c != null) {
                c.affichierOperations();
            } else {
                System.out.println("==> Compte non trouvé!");
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'affichage des opérations: " + e.getMessage());
        }
    }

    public void afficherDetailsCompte(String compteCode) {
        try {
            Compte c = comptes.get(compteCode);
            if (c != null) {
                System.out.println(c.afficherDetails());
            } else {
                System.out.println("==> Compte non trouvé!");
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'affichage des détails: " + e.getMessage());
        }
    }

    public void virementVersCompte(String compteSource, String compteDestinataire, double montant) {
        try {
            if (montant <= 0) {
                System.out.println("==> Montant invalide!");
                return;
            }

            if (compteSource == null || compteDestinataire == null) {
                System.out.println("==> Codes de compte invalides!");
                return;
            }

            Compte cSource = comptes.get(compteSource);
            Compte cDestinataire = comptes.get(compteDestinataire);

            if (cSource == null) {
                System.out.println("==> Compte source non trouvé!");
                return;
            }

            if (cDestinataire == null) {
                System.out.println("==> Compte destinataire non trouvé!");
                return;
            }

            if (compteSource.equals(compteDestinataire)) {
                System.out.println("==> Impossible de faire un virement vers le même compte!");
                return;
            }

            if (cSource.getSolde() < montant) {
                System.out.println("==> Solde insuffisant! Solde disponible: " + cSource.getSolde());
                return;
            }

            cSource.retirer(montant);
            cDestinataire.setSolde(cDestinataire.getSolde() + montant);

            cSource.ajouterOperation(new Retrait(montant, "Virement sortant vers " + compteDestinataire));
            cDestinataire.ajouterOperation(new Versement(montant, "Virement entrant de " + compteSource));

            System.out.println("==> Virement de " + montant + " effectué avec succès!");
            System.out.println("   De: " + compteSource + " vers: " + compteDestinataire);
            System.out.println("   Nouveau solde: " + cSource.getSolde());

        } catch (Exception e) {
            System.out.println("==> Erreur lors du virement: " + e.getMessage());
        }
    }
}