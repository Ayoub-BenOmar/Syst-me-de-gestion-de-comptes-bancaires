package model.compte;

public abstract class Compte {
    protected String code;
    protected double solde;

    public Compte(String code, double solde){
        this.code = code;
        this.solde = solde;
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
