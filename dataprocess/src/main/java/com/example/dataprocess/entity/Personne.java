package com.example.dataprocess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Personne {
    @Id
    private int ID;
    private String prenom;
    private String nom;
    private int age;


    public Personne(int id, String prenom, String nom, int age) {
        this.ID = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public Personne() {

    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID= id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
