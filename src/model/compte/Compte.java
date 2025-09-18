package model.compte;

public abstract class Compte {
    protected String code;
    protected double solde;

    static int compteur = 10000;

    public Compte(){
        this.code = "CPT-" + (compteur++);
        this.solde = 5000;
    }

    public abstract void retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public String getCode(){
        return code;
    }

    public double getSolde(){
        return solde;
    }

    public void setSolde(double solde){
        this.solde = solde;
    }
}

// ctrl+ alt +l
