package model.compte;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant() {
        super();
    }

    public void retirer(double montant){
        if (montant <= 0 ){
            System.out.println("Votre montant: " + montant + " est inavlide!");
            return;
        }

        if(solde - montant < -decouvert){
            System.out.println("❌ Retrait impossible, vous depasser votre decouvert!");
        } else {
            solde -= montant;
            System.out.println("✅ Retrait de montant: " + montant + " effectué avec success, votre nouveau solde est: " + solde);
        }
    }

    public double calculerInteret(){
        return 0;
    }

    public String afficherDetails(){
        return ("Compte courant [ Code: " + code + ", Solde: " + solde + ", Decouvert autorisé: " + decouvert + " ]");
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
