package com.example.crudscolarite.Beans;

public class Matiere {
private int code_mat;
private String nom_mat;
private int coef;
private String nom_spec;
private int id_niv;


    public Matiere() {
    }

    public Matiere(String nom_mat, int coef, String nom_spec, int id_niv) {
        this.nom_mat = nom_mat;
        this.coef = coef;
        this.nom_spec = nom_spec;
        this.id_niv = id_niv;
    }

    public Matiere(int code_mat, String nom_mat, int coef, String nom_spec, int id_niv) {
        this.code_mat = code_mat;
        this.nom_mat = nom_mat;
        this.coef = coef;
        this.nom_spec = nom_spec;
        this.id_niv = id_niv;
    }

    public int getCode_mat() {
        return code_mat;
    }

    public void setCode_mat(int code_mat) {
        this.code_mat = code_mat;
    }

    public String getNom_mat() {
        return nom_mat;
    }

    public void setNom_mat(String nom_mat) {
        this.nom_mat = nom_mat;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
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
