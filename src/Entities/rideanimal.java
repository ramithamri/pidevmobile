/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author chams
 */
public class rideanimal {
    private int id;
    private String citySource;
    private String placeSource;
    private String cityDestination;
    private String placeDestination;
    private String speciesAnimal;
    private String specialNeeds;

    public rideanimal() {
    }

    public rideanimal(String citySource, String placeSource, String cityDestination, String placeDestination, String speciesAnimal, String specialNeeds) {
        this.citySource = citySource;
        this.placeSource = placeSource;
        this.cityDestination = cityDestination;
        this.placeDestination = placeDestination;
        this.speciesAnimal = speciesAnimal;
        this.specialNeeds = specialNeeds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final rideanimal other = (rideanimal) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rideanimal{" + "id=" + id + ", citySource=" + citySource + ", placeSource=" + placeSource + ", cityDestination=" + cityDestination + ", placeDestination=" + placeDestination + ", speciesAnimal=" + speciesAnimal + ", specialNeeds=" + specialNeeds + '}';
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

    public String getPlaceSource() {
        return placeSource;
    }

    public void setPlaceSource(String placeSource) {
        this.placeSource = placeSource;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public String getPlaceDestination() {
        return placeDestination;
    }

    public void setPlaceDestination(String placeDestination) {
        this.placeDestination = placeDestination;
    }

    public String getSpeciesAnimal() {
        return speciesAnimal;
    }

    public void setSpeciesAnimal(String speciesAnimal) {
        this.speciesAnimal = speciesAnimal;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }
    
    
    
}
