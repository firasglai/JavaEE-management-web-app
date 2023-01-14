package com.example.crudscolarite.Beans;

public class Enseignant {

    private int idEns;
    private String nomEns, prenomEns, emailEns, telephoneEns;


    public Enseignant() {
    }

    public Enseignant(int idEns, String nomEns, String prenomEns, String emailEns, String telephoneEns) {
        this.idEns = idEns;
        this.nomEns = nomEns;
        this.prenomEns = prenomEns;
        this.emailEns = emailEns;
        this.telephoneEns = telephoneEns;
    }

    public Enseignant(String nomEns, String prenomEns, String emailEns, String telephoneEns) {
        this.nomEns = nomEns;
        this.prenomEns = prenomEns;
        this.emailEns = emailEns;
        this.telephoneEns = telephoneEns;
    }

    public int getIdEns() {
        return idEns;
    }

    public void setIdEns(int idEns) {
        this.idEns = idEns;
    }

    public String getNomEns() {
        return nomEns;
    }

    public void setNomEns(String nomEns) {
        this.nomEns = nomEns;
    }

    public String getPrenomEns() {
        return prenomEns;
    }

    public void setPrenomEns(String prenomEns) {
        this.prenomEns = prenomEns;
    }

    public String getEmailEns() {
        return emailEns;
    }

    public void setEmailEns(String emailEns) {
        this.emailEns = emailEns;
    }

    public String getTelephoneEns() {
        return telephoneEns;
    }

    public void setTelephoneEns(String telephoneEns) {
        this.telephoneEns = telephoneEns;
    }
}



