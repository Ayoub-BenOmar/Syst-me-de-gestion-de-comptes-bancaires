package model.compte;

import model.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> operations;

    static int compteur = 10000;

    public Compte(){
        this.code = "CPT-" + (compteur++);
        this.solde = 0;
        this.operations = new ArrayList<>();
    }

    public void ajouterOperation(Operation op){
        operations.add(op);
    }

    public void affichierOperations(){
        if(operations.isEmpty()){
            System.out.println("Aucune operation touvée");
        } else {
            for (Operation op : operations){
                System.out.println(op.affichierOperation());
            }
        }
    }

    public abstract void retirer(double montant);
    public abstract double calculerInteret();
    public abstract String afficherDetails();

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
