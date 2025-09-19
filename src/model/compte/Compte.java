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
        try {
            this.code = "CPT-" + (compteur++);
            this.solde = 0;
            this.operations = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la création du compte: " + e.getMessage());
        }
    }

    public void ajouterOperation(Operation op){
        try {
            operations.add(op);
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'ajout de l'opération: " + e.getMessage());
        }
    }

    public void affichierOperations(){
        try {
            if(operations.isEmpty()){
                System.out.println("Aucune opération trouvée");
            } else {
                for (Operation op : operations){
                    System.out.println(op.affichierOperation());
                }
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'affichage des opérations: " + e.getMessage());
        }
    }

    public abstract void retirer(double montant);
    public abstract double calculerInteret();
    public abstract String afficherDetails();

    public String getCode(){
        try {
            return code;
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la récupération du code: " + e.getMessage());
            return "CPT-ERROR";
        }
    }

    public double getSolde(){
        try {
            return solde;
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la récupération du solde: " + e.getMessage());
            return 0.0;
        }
    }

    public void setSolde(double solde){
        try {
            this.solde = solde;
        } catch (Exception e) {
            System.out.println("==> Erreur lors de la modification du solde: " + e.getMessage());
        }
    }
}

// ctrl+ alt +l