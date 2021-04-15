package com.example.esigstudentapp;

public class Module {
    private String nomCours;
    private double note;

    private Module(String nomCours, double note){
        this.nomCours = nomCours;
        this.note = note;
    }

    private Module(){

    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
