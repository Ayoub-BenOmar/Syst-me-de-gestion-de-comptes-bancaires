package controller;

import java.util.Scanner;
import java.util.HashMap;
import model.compte.Compte;
import model.compte.CompteCourant;
import model.compte.CompteEpargne;

public class MainController {
    private HashMap<String, Compte> comptes = new HashMap<>();

    public void runApplication(Scanner sc) {
        boolean running = true;

        while(running) {
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
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("===> MENU BANQUE <===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Consulter un compte");
        System.out.println("3. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private void handleCreateAccountMenu(Scanner sc) {
        boolean running2 = true;

        while(running2) {
            showCreateAccountMenu();

            switch (sc.nextInt()) {
                case 1:
                    handleCreateSavingsAccount();
                    break;
                case 2:
                    handleCreateCurrentAccount();
                    break;
                case 3:
                    running2 = false;
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide");
            }
        }
    }

    private void showCreateAccountMenu() {
        System.out.println("===> MENU CRÉER COMPTE <===");
        System.out.println("1. Créer un compte épargne");
        System.out.println("2. Créer un compte courant");
        System.out.println("3. Retour au menu précédent");
        System.out.print("Choisissez une option: ");
    }

    private void handleCreateSavingsAccount() {
        creeCompteEpargne();
    }

    private void handleCreateCurrentAccount() {
        creeCompteCourant();
    }

    private void handleConsultAccountMenu(Scanner sc) {
        System.out.println("Entrer votre compte code: ");
        String compteCode = sc.next();
        Compte compteTrouve = trouverCompte(compteCode);

        if (compteTrouve != null) {
            boolean running3 = true;

            while(running3) {
                showConsultAccountMenu();

                switch(sc.nextInt()) {
                    case 1:
                        handleWithdraw(sc, compteCode);
                        break;
                    case 2:
                        handleDeposit(sc, compteCode);
                        break;
                    case 3:
//                        handleShowOperations(compteCode);
                        break;
                    case 4:
                        handleShowAccountDetails(compteCode);
                        break;
                    case 5:
                        running3 = false;
                        break;
                    default:
                        System.out.println("Veuillez entrer un choix valide");
                }
            }
        } else {
            System.out.println("Compte non trouvé!");
        }
    }

    private void showConsultAccountMenu() {
        System.out.println("===> MENU CONSULTER COMPTE <===");
        System.out.println("1. Retirer");
        System.out.println("2. Faire un dépôt");
        System.out.println("3. Afficher les opérations");
        System.out.println("4. Afficher les détails du compte");
        System.out.println("5. Retour au menu précédent");
        System.out.print("Choisissez une option: ");
    }

    private void handleWithdraw(Scanner sc, String compteCode) {
        System.out.print("Entrer montant: ");
        float montant = sc.nextFloat();
        retirer(compteCode, montant);
    }

    private void handleDeposit(Scanner sc, String compteCode) {
        System.out.print("Entrer le montant à déposer: ");
        float montant = sc.nextFloat();
        depot(compteCode, montant);
    }
//
//    private void handleShowOperations(String compteCode) {
//        afficherOperations(compteCode);
//    }

    private void handleShowAccountDetails(String compteCode) {
        afficherDetailsCompte(compteCode);
    }

    public void creeCompteEpargne(){
        CompteEpargne ce = new CompteEpargne();
        comptes.put(ce.getCode(), ce);
        System.out.println("✅ Votre compte epargne a ete créé: " + ce.getCode());
    }

    public void creeCompteCourant(){
        CompteCourant cc = new CompteCourant();
        comptes.put(cc.getCode(), cc);
        System.out.println("✅ Votre compte courant a ete créé: " + cc.getCode());
    }

    public Compte trouverCompte(String code){
        Compte c = comptes.get(code);
        if(c == null){
            System.out.println("❌ Le compte n'existe pas");
            return null;
        } else {
            System.out.println("✅ Bienvenue");
            return c;
        }
    }

    public void retirer(String code, float montant){
        Compte c = comptes.get(code);
        if (c != null) {
            c.retirer(montant);
        }
    }

    public void depot(String code, float montant){
        if (montant <= 0){
            System.out.println("❌ Montant invalide!");
            return;
        }
        Compte c = comptes.get(code);
        if (c != null) {
            c.setSolde(c.getSolde() + montant);
            System.out.println("✅ Versement de " + montant + " effectué avec succès!");
        }
    }
//
//    public void afficherOperations(String compteCode) {
//        Compte c = comptes.get(compteCode);
//        if (c != null) {
//            c.afficherOperations();
//        }
//    }

    public void afficherDetailsCompte(String compteCode) {
        Compte c = comptes.get(compteCode);
        System.out.println(c.afficherDetails());
    }
}