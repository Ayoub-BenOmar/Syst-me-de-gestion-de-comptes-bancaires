package model.compte;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant() {
        super();
    }

    public void retirer(double montant){
        try {
            if (montant <= 0 ){
                System.out.println("\n==> Votre montant: " + montant + " est invalide!");
                return;
            }

            if(getSolde() <= 0){
                System.out.println("\n==> Votre solde est insuffisant.");
                return;
            }

            if(getSolde() - montant < -decouvert){
                System.out.println("\n==> Retrait impossible, vous dépassez votre découvert!");
            } else {
                setSolde(getSolde() - montant);
                System.out.println("\n==> Retrait de montant: " + montant + " effectué avec succès, votre nouveau solde est: " + solde);
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors du retrait: " + e.getMessage());
        }
    }

    public double calculerInteret(){
        try {
            return 0;
        } catch (Exception e) {
            System.out.println("==> Erreur lors du calcul des intérêts: " + e.getMessage());
            return 0;
        }
    }

    public String afficherDetails(){
        try {
            return ("\nCompte courant [ Code: " + code + ", Solde: " + solde + ", Découvert autorisé: " + decouvert + " ]");
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'affichage des détails: " + e.getMessage());
            return "Erreur d'affichage";
        }
    }

    public void setDecouvert(double decouvert) {
        try {
            this.decouvert = decouvert;
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la modification du découvert: " + e.getMessage());
        }
    }
}