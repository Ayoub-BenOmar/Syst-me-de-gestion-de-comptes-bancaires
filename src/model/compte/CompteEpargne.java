package model.compte;

public class CompteEpargne extends Compte {
    private static double tauxInteret = 0.05;

    public CompteEpargne() {
        super();
    }

    public void retirer(double montant){
        if(montant <= 0){
            System.out.println("Le montant est ivalide.");
            return;
        }
        if(montant <= solde){
            solde -= montant;
            System.out.println("✅ Retrait de montant: " + montant + " est effectué, votre solde est: " + solde + ".");
        } else {
            System.out.println("Votre solde est insuffisant!");
        }
    }

    public double calculerInteret(){
        return solde * tauxInteret;
    }

    public String afficherDetails(){
        return ("Compte Épargne [ Code= " + code + ", Solde= " + solde + ", Taux d'intérêt= " + tauxInteret + " ]");
    }

    public void deposer(double montant){
        if(montant <= 0){
            System.out.println("❌ Le montant est ivalide.");
        } else {
            setSolde(solde + montant);
            System.out.println("✅ Déposé avec succès");
        }
    }
}
