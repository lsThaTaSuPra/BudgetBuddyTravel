package com.example.budgetbuddytravel.model;

import java.util.Date;

public class Depense {
    private int id;
    private String nom;
    private float montant;
    private Date date;
    private String description;

    public Depense() {
    }

    public Depense(int id, String nom, float montant, Date date, String description) {
        this.id = id;
        this.nom = nom;
        this.montant = montant;
        this.date = date;
        this.description = description;
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

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Depense{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", montant=" + montant +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
