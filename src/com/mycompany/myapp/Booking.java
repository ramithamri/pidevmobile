/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Deathscythvi
 */
public class Booking {
    private int idridedriver;
    private int idUser;

    public int getIdridedriver() {
        return idridedriver;
    }

    public void setIdridedriver(int idridedriver) {
        this.idridedriver = idridedriver;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Booking(int idridedriver, int idUser) {
        this.idridedriver = idridedriver;
        this.idUser = idUser;
    }
    
}
