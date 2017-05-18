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
import com.codename1.ui.EncodedImage;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Boutayta
 */
public class ListePackages {
    private Form current, flistepackage;
    //private Resources theme;
    private Container uicontainer3;
    private ArrayList<RidePassenger> listetrajets = new ArrayList<>();
    private ArrayList<RidePackage> listepaquets = new ArrayList<>();

    public ListePackages(Resources theme) 
    {
        UIBuilder uib = new UIBuilder();
         uicontainer3 = uib.createContainer(theme, "LISTE PACKAGE");

        flistepackage = (Form) uicontainer3;
        
        final ComboBox<RidePassenger> ListetrajetCombo = initListeTrajet();
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);

        flistepackage = initListe(theme);
        try {
            flistepackage.getToolbar().addCommandToRightBar(null, EncodedImage.create("/camion1.png"), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    AddPackage1 fpackage = new AddPackage1(theme);
                    fpackage.show();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        flistepackage.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });
    }
    public ComboBox<RidePassenger> initListeTrajet() 
    {
        final ConnectionRequest con = new ConnectionRequest();
        final ComboBox<RidePassenger> ListetrajetCombo = new ComboBox();

        con.setUrl("http://localhost/PidevMobile/scripts/hamza/select.php?table=ridepassenger");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listetrajets = getListRidePassenger(new String(con.getResponseData()));
                for (RidePassenger r : listetrajets) {
                    ListetrajetCombo.addItem(r);
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        return ListetrajetCombo;
    }

    public ArrayList<RidePassenger> getListRidePassenger(String json) {
        ArrayList<RidePassenger> listrides = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rides = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> listetrajet = (List<Map<String, Object>>) rides.get("ridepassenger");

            for (Map<String, Object> obj : listetrajet) {
                RidePassenger e = new RidePassenger(Integer.parseInt(obj.get("id").toString()), 
                        obj.get("citySource").toString(), 
                        Double.parseDouble(obj.get("longSource").toString()), 
                        Double.parseDouble(obj.get("latSource").toString()), 
                        obj.get("cityDestination").toString(), 
                        Double.parseDouble(obj.get("longDestination").toString()), 
                        Double.parseDouble(obj.get("latDestination").toString()));
                listrides.add(e);

            }

        } catch (IOException ex) {
        }
        return listrides;

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

    public Form initListe(Resources theme) {
        Form f = new Form();
        final ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/PidevMobile/scripts/hamza/select.php?table=ridepackage");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listepaquets = getListRidePackage(new String(con.getResponseData()));
                Collections.reverse(listepaquets);
                Collections.reverse(listetrajets);
                for (RidePackage r : listepaquets) {
                    for (RidePassenger p : listetrajets) {
                        if (r.getIdTrajet() == p.getId()) {
                            f.refreshTheme();
                            Button view = new Button("Choufni") ;
                            view.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println("Trajet id : " + r.getIdTrajet());
                                    System.out.println("Package id : " + r.getId());
                                    ShowPackage shp = new ShowPackage(theme, r, p) ;
                                    shp.show();
                                }
                            });
                            Container fLayout = FlowLayout.encloseIn(new Label("Nom : " + r.getTypePackage()),
                                                 new Label("Direction : " + p.getCitySource() + " ==> " + p.getCityDestination()),
                                                 new Label("Prix : " + Integer.toString(r.getPrix())),
                                                 view,
                                                 new Label("------------------------------------------------------------")) ;
                            f.add(fLayout) ;
                        }
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        return f;
    }
    public void show () {
        flistepackage.show();
    }
}
