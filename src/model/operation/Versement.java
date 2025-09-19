package model.operation;

public class Versement extends Operation{
    private String source;

    public Versement(double montant, String source){
        super(montant);
        this.source = source;
    }

    public String affichierOperation(){
        return("Operation numero: " + numero + ", Date: " + date + ", Montant: " + montant + ", Source: " + source + ".");
    }
}
