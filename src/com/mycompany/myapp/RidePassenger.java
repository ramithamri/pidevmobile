/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author SALMA
 */
public class RidePassenger {
    public int id ;  
    public int price ; 
    public String citySource ; 
    public String cityDestination ; 

    public RidePassenger(int price, String citySource, String cityDestination) {
        this.price = price;
        this.citySource = citySource;
        this.cityDestination = cityDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RidePassenger(int id, int price, String citySource, String cityDestination) {
        this.id = id;
        this.price = price;
        this.citySource = citySource;
        this.cityDestination = cityDestination;
    }
    

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCitySource() {
        return citySource;
    }

    public void setCitySource(String citySource) {
        this.citySource = citySource;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    @Override
    public String toString() {
        return "RidePassenger{" + "price=" + price + ", citySource=" + citySource + ", cityDestination=" + cityDestination + '}';
    }
    
    
}
