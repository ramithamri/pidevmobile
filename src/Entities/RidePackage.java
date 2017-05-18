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
public class RidePackage {
    private int id ;
    private int idTrajet;
    private String Description ;
    private String size ;
    private String typePackage ;
    private int quantity ;
    private double poids ;
    private int prix ;
    private int idUser ;

    public RidePackage() {
    }

    public RidePackage(int id, int idTrajet, String Description, String size, String typePackage, int quantity, double poids, int prix, int idUser) {
        this.id = id;
        this.idTrajet = idTrajet;
        this.Description = Description;
        this.size = size;
        this.typePackage = typePackage;
        this.quantity = quantity;
        this.poids = poids;
        this.prix = prix;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(String typePackage) {
        this.typePackage = typePackage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "RidePackage{" + "id=" + id + ", idTrajet=" + idTrajet + ", Description=" + Description + ", size=" + size + ", typePackage=" + typePackage + ", quantity=" + quantity + ", poids=" + poids + ", prix=" + prix + ", idUser=" + idUser + '}';
    }
    
}
