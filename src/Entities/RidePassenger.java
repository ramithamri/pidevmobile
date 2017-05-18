/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Boutayta
 */
public class RidePassenger {
    private int id ;
    private String citySource ;
    private double longSource ;
    private double latSource ;
    private String cityDestination ;
    private double longDestination ; 
    private double latDestination ;

    public RidePassenger(int id, String citySource, double longSource, double latSource, String cityDestination, double longDestination, double latDestination) {
        this.id = id;
        this.citySource = citySource;
        this.longSource = longSource;
        this.latSource = latSource;
        this.cityDestination = cityDestination;
        this.longDestination = longDestination;
        this.latDestination = latDestination;
    }

    public RidePassenger() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitySource() {
        return citySource;
    }

    public void setCitySource(String citySource) {
        this.citySource = citySource;
    }

    public double getLongSource() {
        return longSource;
    }

    public void setLongSource(double longSource) {
        this.longSource = longSource;
    }

    public double getLatSource() {
        return latSource;
    }

    public void setLatSource(double latSource) {
        this.latSource = latSource;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public double getLongDestination() {
        return longDestination;
    }

    public void setLongDestination(double longDestination) {
        this.longDestination = longDestination;
    }

    public double getLatDestination() {
        return latDestination;
    }

    public void setLatDestination(double latDestination) {
        this.latDestination = latDestination;
    }

    @Override
    public String toString() {
        return "" + citySource + " ==> " + cityDestination ;
    }
    
}
