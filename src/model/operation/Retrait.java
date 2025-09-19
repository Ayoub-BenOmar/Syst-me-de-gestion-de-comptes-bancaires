package model.operation;

public class Retrait extends Operation{
    private String destination;

    public Retrait(double montant, String destination){
        super(montant);
        this.destination = destination;
    }

    public String affichierOperation(){
        return("Operation numero: " + numero + ", Date: " + date + ", Montant: " + montant + ", Destination: " + destination + ".");
    }
}
