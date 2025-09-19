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
        try {
            if(montant <= 0){
                System.out.println("==> Le montant est invalide.");
                return;
            }
            if(montant <= solde){
                solde -= montant;
                System.out.println("==> Retrait de montant: " + montant + " est effectué, votre solde est: " + solde + ".");
            } else {
                System.out.println("==> Votre solde est insuffisant!");
            }
        } catch (Exception e) {
            System.out.println("==> Erreur lors du retrait: " + e.getMessage());
        }
    }

    public double calculerInteret(){
        try {
            return solde * tauxInteret;
        } catch (Exception e) {
            System.out.println("==> Erreur lors du calcul des intérêts: " + e.getMessage());
            return 0;
        }
    }

    public void demarrerCalculInteret(){
        try {
            Runnable task = () -> {
                try {
                    double interet = calculerInteret();
                    setSolde(getSolde() + interet);
                } catch (Exception e) {
                    System.out.println("==> Erreur lors de l'ajout d'intérêts: " + e.getMessage());
                }
            };

            scheduler.scheduleAtFixedRate(task, 5, 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("==> Erreur lors du démarrage du calcul d'intérêts: " + e.getMessage());
        }
    }

    public String afficherDetails(){
        try {
            return ("Compte Épargne [ Code: " + code + ", Solde: " + getSolde() + ", Taux d'intérêt: " + tauxInteret + " ]");
        } catch (Exception e) {
            System.out.println("==> Erreur lors de l'affichage des détails: " + e.getMessage());
            return "Erreur d'affichage";
        }
    }
}