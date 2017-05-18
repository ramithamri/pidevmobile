/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entities.RidePackage;
import Entities.RidePassenger;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Boutayta
 */
public class ShowPackage {
    private Form fshowpackage ;
    private Container uicontainer ;
    private ArrayList<RidePassenger> listetrajets = new ArrayList<>();
    private ArrayList<RidePackage> listepaquets = new ArrayList<>();
    public ShowPackage(Resources theme, RidePackage ridepackage, RidePassenger ridepassenger) {
        UIBuilder uib = new UIBuilder();
        uicontainer = uib.createContainer(theme, "SHOW PACKAGE") ;
        fshowpackage = (Form) uicontainer ;
        /*final RidePassenger ridepassenger = initListeTrajet(IdTrajet) ;
        final RidePackage ridepackage = initListePackage(IdPackage) ;*/
        Button trajet = new Button("Voir Trajet") ;
        trajet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Direction : " + ridepassenger.getCitySource() + " ==> " + ridepassenger.getCityDestination());
                System.out.println("Type : " + ridepackage.getTypePackage());
                ShowMapPackage shmp = new ShowMapPackage(theme, ridepackage, ridepassenger) ;
                shmp.show();
            }
        });
        Button supprimer = new Button("Fasa5ni") ;
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SupprimerMarchandise(ridepackage);
                Dialog.show("Suppression terminé","Votre Marchandise a été supprimer", "OK", null) ;
                ListePackages lst = new ListePackages(theme) ;
                lst.show();
            }
        });
        Container flowLayout = FlowLayout.encloseIn(
        new Label("Type : " + ridepackage.getTypePackage()),
        new Label("Taille de la marchandise : " + ridepackage.getSize()),
        new Label("Quantité : " + ridepackage.getQuantity()),
        new Label("Poids : " + ridepackage.getPoids()),
        new Label("Description : " + ridepackage.getDescription()),
        new Label("Prix : " + ridepackage.getPrix()),
        trajet,supprimer);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon= FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
        fshowpackage.getToolbar().addCommandToLeftBar(null, icon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListePackages lst = new ListePackages(theme) ;
                lst.show();
            }
        });
        fshowpackage.add(flowLayout) ;
    }
    public void SupprimerMarchandise (RidePackage r) {
        final ConnectionRequest con = new ConnectionRequest();
        final RidePassenger e = new RidePassenger();

        con.setUrl("http://localhost/PidevMobile/scripts/hamza/delete.php?table=ridepackage&idpackage=" + r.getId());
        NetworkManager.getInstance().addToQueue(con);
    }
    public RidePassenger initListeTrajet(int IdTrajet) {
        final ConnectionRequest con = new ConnectionRequest();
        final RidePassenger e = new RidePassenger();

        con.setUrl("http://localhost/PidevMobile/scripts/hamza/select.php?table=ridepassenger");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listetrajets = getListRidePassenger(new String(con.getResponseData()));
                for (RidePassenger r : listetrajets) {
                    if (r.getId() == IdTrajet) {
                        e.setId(r.getId());
                        e.setCitySource(r.getCitySource());
                        e.setLongSource(r.getLongSource());
                        e.setLatSource(r.getLatSource());
                        e.setCityDestination(r.getCityDestination());
                        e.setLongDestination(r.getLongDestination());
                        e.setLatDestination(r.getLatDestination());
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        return e;
    }
    public ArrayList<RidePassenger> getListRidePassenger(String json) {
        ArrayList<RidePassenger> listrides = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rides = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> listetrajet = (List<Map<String, Object>>) rides.get("ridepassenger");

            for (Map<String, Object> obj : listetrajet) {
                RidePassenger e = new RidePassenger(Integer.parseInt(obj.get("id").toString()), obj.get("citySource").toString(), Double.parseDouble(obj.get("longSource").toString()), Double.parseDouble(obj.get("latSource").toString()), obj.get("cityDestination").toString(), Double.parseDouble(obj.get("longDestination").toString()), Double.parseDouble(obj.get("latDestination").toString()));
                listrides.add(e);

            }

        } catch (IOException ex) {
        }
        return listrides;

    }
    public RidePackage initListePackage(int IdPackage) {
        final ConnectionRequest con = new ConnectionRequest();
        final RidePackage e = new RidePackage();

        con.setUrl("http://localhost/PidevMobile/scripts/hamza/select.php?table=ridepackage");
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listepaquets = getListRidePackage(new String(con.getResponseData()));
                for (RidePackage r : listepaquets) {
                    //System.out.println(r);
                    if (r.getId() == IdPackage) {
                        e.setId(r.getId());
                        e.setIdTrajet(r.getIdTrajet());
                        e.setDescription(r.getDescription());
                        e.setIdUser(r.getIdUser());
                        e.setPoids(r.getPoids());
                        e.setPrix(r.getPrix());
                        e.setQuantity(r.getQuantity());
                        e.setSize(r.getSize());
                        e.setTypePackage(r.getTypePackage());   
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        return e;
    }
    public ArrayList<RidePackage> getListRidePackage(String json) {
        ArrayList<RidePackage> listrides = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rides = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> listetrajet = (List<Map<String, Object>>) rides.get("ridepackage");

            for (Map<String, Object> obj : listetrajet) {
                RidePackage e = new RidePackage(Integer.parseInt(obj.get("id_package").toString()), Integer.parseInt(obj.get("idtrajet").toString()), obj.get("description").toString(), obj.get("size").toString(), obj.get("typePackage").toString(), Integer.parseInt(obj.get("quantity").toString()), Double.parseDouble(obj.get("poids").toString()), Integer.parseInt(obj.get("prix").toString()), Integer.parseInt(obj.get("idUser").toString()));
                listrides.add(e);

            }

        } catch (IOException ex) {
        }
        return listrides;

    }
    public void show () {
        fshowpackage.show();
    }
}
