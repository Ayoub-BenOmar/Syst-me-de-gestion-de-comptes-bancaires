# 💳 Application de Gestion Bancaire (Java 8 - Console)

## 📌 Contexte du projet
Une banque souhaite automatiser la gestion de ses comptes via un système informatisé.  
Votre mission est de développer une **application console en Java 8** pour gérer efficacement les comptes bancaires et leurs opérations (versements, retraits, virements).

✨ **Note :** Il s’agit de mon **premier projet réalisé en Java**, dans un cadre académique

---

## 🏗️ Architecture du projet
L’application est organisée selon une architecture **MVC simplifiée** :

- **Couche de présentation (UI/Menu)**  
  → Affiche les menus dans la console et récupère les entrées de l’utilisateur.
- **Couche métier (Model)**  
  → Contient les classes métiers (`Compte`, `CompteCourant`, `CompteEpargne`, `Operation`, etc.).
- **Couche contrôleur**  
  → Contient la logique applicative (création de comptes, gestion des retraits, virements, etc.).
- **Couche utilitaire** *(optionnelle)*  
  → Fonctions communes comme la génération de codes de compte, la validation des entrées, etc.

---

## 📂 Structure des classes

### 🔹 Classe abstraite `Compte`
- **Attributs :**
    - `code` (format `CPT-XXXXX`, avec 5 chiffres)
    - `solde`
    - `listeOperations`
- **Méthodes abstraites :**
    - `retirer()`
    - `calculerInteret()`
    - `afficherDetails()`

### 🔹 Classe `CompteCourant` (hérite de `Compte`)
- **Attribut supplémentaire :** `decouvert`
- **Règles :**
    - `calculerInteret()` retourne **0**
    - Le retrait ne peut pas rendre le solde inférieur à `-decouvert`

### 🔹 Classe `CompteEpargne` (hérite de `Compte`)
- **Attribut supplémentaire :** `tauxInteret`
- **Règles :**
    - `calculerInteret()` calcule les intérêts selon le taux
    - Le retrait n’est possible que si le solde ≥ montant à retirer

### 🔹 Classe abstraite `Operation`
- **Attributs :**
    - `numero` (UUID, identifiant unique)
    - `date`
    - `montant`

### 🔹 Classe `Versement` (hérite d'`Operation`)
- **Attribut supplémentaire :** `source` (ex: "Virement externe", "Dépôt espèces", "Salaire")

### 🔹 Classe `Retrait` (hérite d'`Operation`)
- **Attribut supplémentaire :** `destination` (ex: "Distributeur ATM", "Chèque", "Virement sortant")

---

## ⚙️ Fonctionnalités principales
- ✅ Créer un compte (épargne ou courant)
- ✅ Effectuer un versement dans un compte
- ✅ Effectuer un retrait d’un compte
- ✅ Effectuer un virement entre deux comptes
- ✅ Consulter le solde d’un compte
- ✅ Consulter la liste des opérations effectuées sur un compte
- ✅ Afficher les détails d’un compte

---

## 🖥️ Interface utilisateur (console)
Un menu interactif permet :

### **Menu Banque**
1. Créer un compte
2. Consulter un compte
3. Quitter

### **Menu Consulter Compte**
1. Retirer
2. Faire un virement
3. Afficher les opérations
4. Afficher les détails du compte
5. Retour au menu précédent

---

## 🔧 Spécifications techniques
- **Langage :** Java 8
- **Stockage :** `ArrayList` et `HashMap` pour gérer comptes et opérations
- **Dates :** utilisation de `java.time` (API Date/Time)
- **Validation des données :**
    - Montants doivent être positifs
    - Code compte au format `CPT-XXXXX`
- **Gestion des exceptions :** `try/catch` pour sécuriser les entrées
- **Persistance :** les données restent en mémoire tant que l’application est en cours d’exécution

---

## 🚀 Lancer le projet
1. Ouvrir le projet dans **IntelliJ IDEA** (ou tout IDE Java compatible).
2. Compiler et exécuter la classe `Main`.
3. Suivre les instructions dans la console.

---

👨‍💻 **Projet académique – Gestion de comptes bancaires**
