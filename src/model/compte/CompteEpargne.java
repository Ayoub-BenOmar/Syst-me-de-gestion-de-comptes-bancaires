package model.compte;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CompteEpargne extends Compte {
    private static double tauxInteret = 0.05;
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public CompteEpargne() {
        super();
        demarrerCalculInteret();
    }

    public void retirer(double montant){
        if(montant <= 0){
            System.out.println("❌ Le montant est ivalide.");
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

    public void demarrerCalculInteret(){

        Runnable task = () ->{
            double interet = calculerInteret();
            setSolde(getSolde() + interet);
        };

        scheduler.scheduleAtFixedRate(task, 5, 5, TimeUnit.SECONDS);
    }

    public String afficherDetails(){
        return ("Compte Épargne [ Code: " + code + ", Solde: " + getSolde() + ", Taux d'intérêt: " + tauxInteret + " ]");
    }
}
