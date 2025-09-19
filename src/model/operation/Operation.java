package model.operation;

import java.util.Date;
import java.util.UUID;

public abstract class Operation {
    protected String numero;
    protected Date date;
    protected double montant;

    public Operation(double montant){
        this.numero = UUID.randomUUID().toString();
        this.date = new Date();
        this.montant = montant;
    }

    public abstract String affichierOperation();
}
