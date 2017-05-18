/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

/**
 *
 * @author nader
 */
public class Etudiant {
    private String nom;
    private String cin;
    private int age;

    public Etudiant(String nom, String cin, int age) {
        this.nom = nom;
        this.cin = cin;
        this.age = age;
    }

    public Etudiant() {
    }

    public int getAge() {
        return age;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "nom=" + nom + ", cin=" + cin + ", age=" + age + '}';
    }

  
    
    
    
    
          
}
