package com.example.crudscolarite.Beans;

public class Etudiant {
    private int idEtu;
    private String nomEtu, prenomEtu, emailEtu;
     private String groupNom;

    public Etudiant() {
    }

    public Etudiant(String nomEtu, String prenomEtu, String emailEtu, String groupNom) {
        this.nomEtu = nomEtu;
        this.prenomEtu = prenomEtu;
        this.emailEtu = emailEtu;
        this.groupNom = groupNom;
    }

    public Etudiant(int idEtu, String nomEtu, String prenomEtu, String emailEtu, String groupNom) {
        this.idEtu = idEtu;
        this.nomEtu = nomEtu;
        this.prenomEtu = prenomEtu;
        this.emailEtu = emailEtu;
        this.groupNom = groupNom;
    }

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }

    public String getNomEtu() {
        return nomEtu;
    }

    public void setNomEtu(String nomEtu) {
        this.nomEtu = nomEtu;
    }

    public String getPrenomEtu() {
        return prenomEtu;
    }

    public void setPrenomEtu(String prenomEtu) {
        this.prenomEtu = prenomEtu;
    }

    public String getEmailEtu() {
        return emailEtu;
    }

    public void setEmailEtu(String emailEtu) {
        this.emailEtu = emailEtu;
    }

    public String getGroupNom() {
        return groupNom;
    }

    public void setGroupNom(String groupNom) {
        this.groupNom = groupNom;
    }
}
