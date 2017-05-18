

package com.mycompany.myapp;

public class Ridedriver {

    public Ridedriver() {
    }

   
    private int id;
    private double price;
    private String citysource;
    private String citydestination;
    private int nbrPlaces;

    public Ridedriver(double price,String citysource, String citydestination, int nbrPlaces) {
        this.price=price;
        this.citysource = citysource;
        this.citydestination = citydestination;
        this.nbrPlaces = nbrPlaces;
    }
     public Ridedriver(int id,double price,String citysource, String citydestination, int nbrPlaces) {
         this.id=id;
        this.price=price;
        this.citysource = citysource;
        this.citydestination = citydestination;
        this.nbrPlaces = nbrPlaces;
    }
 public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getCitysource() {
        return citysource;
    }

    public void setCitysource(String citysource) {
        this.citysource = citysource;
    }

    public String getCitydestination() {
        return citydestination;
    }

    public void setCitydestination(String citydestination) {
        this.citydestination = citydestination;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    @Override
    public String toString() {
        return "Ridedriver{"+"id="+id+"price="+price + "citysource=" + citysource + ", citydestination=" + citydestination + ", nbrPlaces=" + nbrPlaces + '}';
    }
}

  