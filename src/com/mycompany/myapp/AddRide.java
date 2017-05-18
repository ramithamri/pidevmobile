/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author SALMA
 */
public class AddRide extends Form {

    public Container mainContainer;
    public Label l1, l2, l3, l4, l5, l6;
    public TextField priceTf, destinationTf, sourceTf;
    public Button addride;

    public AddRide() {
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));
        l1 = new Label("Add your Ride ");
        l2 = new Label("your Price in DT");
        priceTf = new TextField("");
        l3 = new Label("Source:");
        sourceTf = new TextField("");
        l4 = new Label("Destination :");
        l5 = new Label("");
        l6 = new Label("");
        destinationTf = new TextField("");
        addride = new Button("add Ride");
        mainContainer.add(l1);
        mainContainer.add(l5);
        mainContainer.add(l2);
        mainContainer.add(priceTf);
        
        mainContainer.add(l3);
        mainContainer.add(sourceTf);
         mainContainer.add(l4);
        mainContainer.add(destinationTf);
     
        mainContainer.add(l6);
        mainContainer.add(addride);
       
        addride.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a ride
            RidePassenger ride = new RidePassenger(Integer.parseInt(priceTf.getText()), sourceTf.getText(), destinationTf.getText());
            new ServiceRide().addRide(ride);
        });
        Command backBtn = Back.createBackBtn();
        this.getToolbar().addCommandToRightBar(Back.createBackBtn());
        this.add(BorderLayout.NORTH, mainContainer);

    }

}
