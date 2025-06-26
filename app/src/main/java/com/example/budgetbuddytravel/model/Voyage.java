package com.example.budgetbuddytravel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Voyage {
    private int id;
    private String nom;
    private String destination;
    private Date dateDepart;
    private Date dateRetour;
    private float budgetGlobal;
    private List<CategorieDepense> categories = new ArrayList<>();

    public void ajouterVoyage() {

    }

    public void supprimerVoyage() {

    }
}
