/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entities.RidePackage;
import Entities.RidePassenger;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Boutayta
 */
public class ShowMapPackage {
    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private Form fmap ;
    public ShowMapPackage(Resources theme, RidePackage ridepackage, RidePassenger ridepassenger) {
        fmap = new Form("Trajet du marchandise") ;
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        Style s1 = new Style();
        s.setBgTransparency(0);
        s.setFgColor(0xff0000);
        FontImage icon= FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
        fmap.getToolbar().addCommandToLeftBar(null, icon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ShowPackage shp = new ShowPackage(theme, ridepackage, ridepassenger) ;
                shp.show();
            }
        });
        MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setCameraPosition(new Coord(35.9701223, 10.1096893));
        cnt.setHeight(8);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_PLACE, s1).toEncodedImage(), new Coord(ridepassenger.getLatSource(), ridepassenger.getLongSource()), ridepassenger.getCitySource(), "Début du Trajet", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Ville de départ",ridepassenger.getCitySource(),"OK",null) ;
            }
        });
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_PLACE, s1).toEncodedImage(), new Coord(ridepassenger.getLatDestination(), ridepassenger.getLongDestination()), ridepassenger.getCityDestination(), "Fin du Trajet", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Ville de destination",ridepassenger.getCityDestination(),"OK",null) ;
            }
        });
        Container flowLayout = FlowLayout.encloseIn(cnt) ;
        fmap.add(flowLayout) ;
    }
    public void show () {
        fmap.show() ;
    }
}
