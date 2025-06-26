package com.example.budgetbuddytravel.model;

public class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private boolean estConnecte = false;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void creerCompte() {
        // Simule la création d’un compte (dans une vraie app, appel backend ici)
        System.out.println("Compte créé pour " + nom + " (" + email + ")");
    }

    public void seConnecter(String email, String motDePasse) {
        if (this.email.equals(email) && this.motDePasse.equals(motDePasse)) {
            estConnecte = true;
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Identifiants incorrects.");
        }
    }

    public void seDeconnecter() {
        estConnecte = false;
        System.out.println("Déconnecté.");
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", connecté=" + estConnecte +
                '}';
    }
}
