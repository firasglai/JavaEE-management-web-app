package com.example.crudscolarite.Beans;

public class Groupe {

    private String nom_grp;
    private String nom_spec;
    private int id_niv;

    public Groupe() {
    }

    public Groupe(String nom_grp) {
        this.nom_grp = nom_grp;
    }

    public Groupe(String nom_grp, String nom_spec) {
        this.nom_grp = nom_grp;
        this.nom_spec = nom_spec;
    }

    public Groupe(String nom_grp, String nom_spec, int id_niv) {
        this.nom_grp = nom_grp;
        this.nom_spec = nom_spec;
        this.id_niv = id_niv;
    }

    public String getNom_grp() {
        return nom_grp;
    }

    public void setNom_grp(String nom_grp) {
        this.nom_grp = nom_grp;
    }

    public String getNom_spec() {
        return nom_spec;
    }

    public void setNom_spec(String nom_spec) {
        this.nom_spec = nom_spec;
    }

    public int getId_niv() {
        return id_niv;
    }

    public void setId_niv(int id_niv) {
        this.id_niv = id_niv;
    }
}
