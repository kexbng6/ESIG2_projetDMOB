package com.example.esigstudentapp;

public class Examen {
    String date, cours, heure, salle, type;

    public Examen(String date, String cours, String salle, String type){
        this.cours = cours;
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.type = type;
    }

    public Examen(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}