package com.example.esigstudentapp;

public class Module {
    private String cours;
    private double note;

    private Module(String nomCours, double note){
        this.cours = nomCours;
        this.note = note;
    }

    private Module(){

    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
