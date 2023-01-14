package com.example.crudscolarite.Beans;

public class Note {


    private int idnote;
    private float note_pratique;
    private float note_controle;
    private float note_examen;
    private int id_etu;
    private int code_mat;
    public Note() {
    }

    public Note(int idnote, float note_pratique, float note_controle, float note_examen, int id_etu, int code_mat) {
        this.idnote = idnote;
        this.note_pratique = note_pratique;
        this.note_controle = note_controle;
        this.note_examen = note_examen;
        this.id_etu = id_etu;
        this.code_mat = code_mat;
    }

    public Note(int idnote, float note_pratique, float note_examen, int id_etu, int code_mat) {
        this.idnote = idnote;
        this.note_pratique = note_pratique;
        this.note_examen = note_examen;
        this.id_etu = id_etu;
        this.code_mat = code_mat;
    }

    public Note(int idnote, float note_controle, int id_etu, int code_mat) {
        this.idnote = idnote;
        this.note_controle = note_controle;
        this.id_etu = id_etu;
        this.code_mat = code_mat;
    }

    public int getIdnote() {
        return idnote;
    }

    public void setIdnote(int idnote) {
        this.idnote = idnote;
    }

    public float getNote_pratique() {
        return note_pratique;
    }

    public void setNote_pratique(float note_pratique) {
        this.note_pratique = note_pratique;
    }

    public float getNote_controle() {
        return note_controle;
    }

    public void setNote_controle(float note_controle) {
        this.note_controle = note_controle;
    }

    public float getNote_examen() {
        return note_examen;
    }

    public void setNote_examen(float note_examen) {
        this.note_examen = note_examen;
    }

    public int getId_etu() {
        return id_etu;
    }

    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

    public int getCode_mat() {
        return code_mat;
    }

    public void setCode_mat(int code_mat) {
        this.code_mat = code_mat;
    }
}
