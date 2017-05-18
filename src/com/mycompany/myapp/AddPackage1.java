package com.mycompany.myapp;

import Entities.RidePassenger;
import Entities.RidePackage;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.UIBuilder;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class AddPackage1 {

    //private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private Form current, faddpackage1, faddpackage2;
    private Resources theme;
    private Container uicontainer1, uicontainer2;
    private TextField TFTypemarchandise, TFpoids;
    private ComboBox quantity, trajetcombo, TFTaillemarchandise;
    private Button addpackage;
    private Label prix, ltypemarchandise, lpoids, ldescription;
    private TextField description;
    private int prixunitaire = 5;
    private int prixfinale = 5;
    private ArrayList<RidePassenger> listetrajets = new ArrayList<>();
    private ArrayList<RidePackage> listepaquets = new ArrayList<>();
    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private int idtrajet = 0;

    public AddPackage1(Resources theme) {
        UIBuilder uib = new UIBuilder();
        uicontainer1 = uib.createContainer(theme, "ADD PACKAGE GOOGLE MAPS");
        uicontainer2 = uib.createContainer(theme, "ADD PACKAGE");
        faddpackage1 = (Form) uicontainer1;
        faddpackage2 = (Form) uicontainer2;
        TFTypemarchandise = (TextField) uib.findByName("type marchandise", uicontainer2);
        TFTaillemarchandise = (ComboBox) uib.findByName("taille marchandise", uicontainer2);
        TFpoids = (TextField) uib.findByName("poids", uicontainer2);
        description = (TextField) uib.findByName("description", uicontainer2);
        quantity = (ComboBox) uib.findByName("quantity", uicontainer2);
        addpackage = (Button) uib.findByName("addpackage", uicontainer2);
        ltypemarchandise = (Label) uib.findByName("LabelTypeMarchandise", uicontainer2);
        lpoids = (Label) uib.findByName("LabelPoids", uicontainer2);
        ldescription = (Label) uib.findByName("LabelDescription", uicontainer2);
        prix = (Label) uib.findByName("Prix", uicontainer2);
        prix.setText("Prix : 5 DT");

        MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setCameraPosition(new Coord(36.7574874, 10.6095672));
        final Button Suivant = new Button("Suivant");
        final ComboBox<RidePassenger> ListetrajetCombo = initListeTrajet();
        Style s = new Style();
        s.setBgTransparency(0);
        s.setFgColor(0xff0000);
        ListetrajetCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cnt.clearMapLayers();
                cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_PLACE, s).toEncodedImage(), new Coord(ListetrajetCombo.getSelectedItem().getLatSource(), ListetrajetCombo.getSelectedItem().getLongSource()), ListetrajetCombo.getSelectedItem().getCitySource(), "Début du Trajet", this);
                cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_PLACE, s).toEncodedImage(), new Coord(ListetrajetCombo.getSelectedItem().getLatDestination(), ListetrajetCombo.getSelectedItem().getLongDestination()), ListetrajetCombo.getSelectedItem().getCityDestination(), "Début du Trajet", this);
                cnt.addPath(new Coord(ListetrajetCombo.getSelectedItem().getLatSource(), ListetrajetCombo.getSelectedItem().getLongSource()),
                        new Coord(ListetrajetCombo.getSelectedItem().getLatDestination(), ListetrajetCombo.getSelectedItem().getLongDestination()));
                idtrajet = ListetrajetCombo.getSelectedItem().getId();
            }
        });
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                prixfinale = (quantity.getSelectedIndex() + 1) * prixunitaire;
                prix.setText("Prix : " + prixfinale + " DT");
            }
        });

        faddpackage1.setLayout(new BorderLayout());
        faddpackage1.add(BorderLayout.CENTER, cnt).add(BorderLayout.NORTH, ListetrajetCombo).add(BorderLayout.SOUTH, Suivant);
        faddpackage1.addCommand(new Command("Ma Position") {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                    System.out.println("Loool0");
                    if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog ipDlg = ip.showInifiniteBlocking();
                        //Cancel after 20 seconds
                        Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                        ipDlg.dispose();
                        if (loc != null) {
                            double lat = loc.getLatitude();
                            double lng = loc.getLongitude();
                            try {
                                System.out.println("Loool1");
                                Display.getInstance().sendSMS("09123456789", "http://maps.google.com/?q=" + lat + "," + lng, false);
                            } catch (IOException ex) {
                                Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
                                ex.printStackTrace();
                            }
                        } else {
                            Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                        }
                    } else {
                        Dialog.show("GPS disabled", "AppName needs access to GPS. Please enable GPS", "Ok", null);
                    }
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog ipDlg = ip.showInifiniteBlocking();
                    //Cancel after 20 seconds
                    Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                    ipDlg.dispose();
                    if (loc != null) {
                        double lat = loc.getLatitude();
                        double lng = loc.getLongitude();
                        try {
                            System.out.println("Loool2");
                            Display.getInstance().sendSMS("09123456789", "http://maps.google.com/?q=" + lat + "," + lng, false);
                        } catch (IOException ex) {
                            Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
                            ex.printStackTrace();
                        }
                    } else {
                        Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                    }
                }
            }
        });
        Suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (idtrajet == 0) {
                    Dialog.show("abscence du trajet", "Veuillez choisir un trajet", "Ok", null);
                } else {
                    faddpackage2.show();
                }
            }
        });
        addpackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                final ConnectionRequest con = new ConnectionRequest();
                if (!"".equals(TFTypemarchandise.getText()) && !"".equals(TFpoids.getText()) && !"".equals(description.getText())) {
                    System.out.println("http://localhost/PidevMobile/scripts/hamza/insert.php?size=" + TFTaillemarchandise.getSelectedItem().toString() + "&typePackage=" + TFTypemarchandise.getText() + "&poids=" + TFpoids.getText() + "&description=" + description.getText() + "&quantity=" + quantity.getSelectedItem().toString() + "&prix=" + prixfinale + "&idUser=2" + "&idtrajet=" + idtrajet);
                    con.setUrl("http://localhost/PidevMobile/scripts/hamza/insert.php?size=" + TFTaillemarchandise.getSelectedItem().toString() + "&typePackage=" + TFTypemarchandise.getText() + "&poids=" + TFpoids.getText() + "&description=" + description.getText() + "&quantity=" + quantity.getSelectedItem().toString() + "&prix=" + prixfinale + "&idUser=2" + "&idtrajet=" + idtrajet);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);
                            if (s.equals("success")) {
                                Dialog.show("Confirmation", "Un paquet a été ajouté", "Ok", null);
                            }
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                } else {
                    Dialog.show("Erreur", "Il manque des champs non traité", "Ok", null);

                    if ("".equals(TFTypemarchandise.getText())) {
                        ltypemarchandise.getAllStyles().setFgColor(ColorUtil.GRAY);
                    }

                    if ("".equals(TFpoids.getText())) {
                        lpoids.getAllStyles().setBgColor(ColorUtil.GRAY);
                    }

                    if ("".equals(description.getText())) {
                        ldescription.getAllStyles().setBgColor(ColorUtil.GRAY);
                    }
                }
            }
        }
        );
        String IntegerConstraint = "^[0-9]";
        Validator v = new Validator() ;
        v.addConstraint(TFpoids, new RegexConstraint(IntegerConstraint, "Veuillez entrer un poids valide")) ;
        Style s1 = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s1);
        faddpackage1.getToolbar().addCommandToLeftBar(null, icon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListePackages lst = new ListePackages(theme);
                lst.show();
            }
        });
        faddpackage2.getToolbar().addCommandToLeftBar(null, icon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddPackage1 add = new AddPackage1(theme);
                add.show();
            }
        });
        faddpackage1.show();
    }

    public ComboBox<RidePassenger> initListeTrajet() {
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
                RidePassenger e = new RidePassenger(Integer.parseInt(obj.get("id").toString()), obj.get("citySource").toString(), Double.parseDouble(obj.get("longSource").toString()), Double.parseDouble(obj.get("latSource").toString()), obj.get("cityDestination").toString(), Double.parseDouble(obj.get("longDestination").toString()), Double.parseDouble(obj.get("latDestination").toString()));
                listrides.add(e);

            }

        } catch (IOException ex) {
        }
        return listrides;

    }

    public void show() 
    {
        faddpackage1.show();
    }
}
