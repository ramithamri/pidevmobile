/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import static com.mycompany.myapp.ServiceRide.x;

/**
 *
 * @author SALMA
 */
public class ViewRide extends Form {

    private final Label l1, l2, l3, l4, l5, l6, l7, l8;
    private final TextField ratingTf;
    public final TextArea titleTf, contentTf;
    private final Container mainContainer, main;
    private final Button removeBtn, editBtn;
    private RidePassenger current;

    public ViewRide(int id, int price, String citySource, String cityDestination) {

        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        main = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));
        l1 = new Label("Rides ");

        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);

        ratingTf = new TextField(price+"");
        
        titleTf = new TextArea(citySource);
        contentTf = new TextArea(cityDestination);
        l2 = new Label("Price:  ");
        l4 = new Label("");
        l7 = new Label("");
        l5 = new Label("");
        l6 = new Label("");
        l8 = new Label("");
        l3 = new Label("Destination : ");
        removeBtn = new Button("Remove");
        editBtn = new Button("Edit");
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Back.setLabelStyle(l2);

        mainContainer.add(l2);
        mainContainer.add(l4);
        mainContainer.add(ratingTf);
        mainContainer.add(l8);
        mainContainer.add(titleTf);
        mainContainer.add(l5);
        Back.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(l6);
        mainContainer.add(contentTf);
        mainContainer.add(l7);

        mainContainer.add(removeBtn);
        mainContainer.add(editBtn);

        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ServiceRide().removeRide(current);
            }
        });
        editBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                current = new RidePassenger(x, Integer.parseInt(ratingTf.getText()), titleTf.getText(), contentTf.getText());
                new ServiceRide().updateRide(current);
            }
        });
        Command backBtn = Back.createBackBtn();
        this.getToolbar().addCommandToRightBar(Back.createBackBtn());
        this.add(BorderLayout.NORTH, mainContainer);

    }

}
