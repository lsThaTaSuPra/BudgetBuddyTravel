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
    public float getMontant() {
        return montant;
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
