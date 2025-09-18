package controller;

import model.compte.Compte;
import model.compte.CompteCourant;
import model.compte.CompteEpargne;

import java.util.ArrayList;
import java.util.HashMap;

public class MainController {
    private HashMap<String, Compte> comptes = new HashMap<>();

    public void creeCompteEpargne(){
        CompteEpargne ce = new CompteEpargne();
        comptes.put(ce.getCode(), ce);
        System.out.println("✅ Votre compte epargne a ete créé: " + ce.getCode());
    }

    public void creeCompteCourant(){
        CompteCourant cc = new CompteCourant();
        comptes.put(cc.getCode(), cc);
        System.out.println("✅ Votre compte courant a ete créé: " + cc.getCode());
    }

    public Compte trouverCompte(String code){
        Compte c = comptes.get(code);
        if(c == null){
            System.out.println("❌ Le compte n'existe pas");
            return null;
        } else {
            System.out.println("✅ Bienvenue");
            return c;
        }
    }

    public void retirer(String code, float montant){
        Compte c = comptes.get(code);
        c.retirer(montant);
    }
}
