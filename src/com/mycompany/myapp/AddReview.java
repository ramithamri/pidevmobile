/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entities.SimpleUser;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import static com.mycompany.myapp.ReviewsService.Re;
import java.util.List;

/**
 *
 * @author Deathscythvi
 */
public class AddReview extends Form {
    public static int x;
    public  Label l1 ; 
    private void initStarRankStyle(Style s, Image star) 
    {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        x = starRank.getIncrements();
        return starRank;
    }

    public AddReview() {
        l1 =  new Label("Add Your Review ");
        l1.setAlignment(RIGHT);
        this.add(l1);
        
        Slider etoiles =  createStarRankSlider();
        this.add(FlowLayout.encloseCenter(etoiles));
        ReviewsService r = new ReviewsService();
        List<String> l = r.findByName();
        System.out.println(Re);

        String[] characters = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            characters[i] = l.get(i);
        }

        AutoCompleteTextField act = new AutoCompleteTextField(characters);
act.addActionListener(e -> ToastBar.showMessage("You picked " + act.getText(), FontImage.MATERIAL_INFO));
        Button down = new Button();
        FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        this.add(
                BorderLayout.center(act).
                add(BorderLayout.EAST, down));
        down.addActionListener(e -> act.showPopup());

        TextField tftitle = new TextField("", "title");
        TextField tfContent = new TextField("", "content");
        this.add(tftitle);
        this.add(tfContent);
        Button btnOk = new Button("Add your Review");
        this.add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int p = etoiles.getProgress();
                SimpleUser.setCurrentId("56");
                int iduser = Integer.parseInt(SimpleUser.getCurrentId());

                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/PidevMobile/scripts/salma/insert.php?rating=" + p + "&title=" + tftitle.getText() + "&content=" + tfContent.getText() + "&idUser2="+59 + "&idUser=" + iduser);

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        System.out.println(x);
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        if (s.equals("success")) {
                            Dialog.show("Review Added", "your Review is added and put into consideration", "Ok", null);
                        } else {
                            Dialog.show("shit !!", "ajout ok", "Ok", null);
                        }
                        new ReviewsService().findAllBooks();
                    }
                });
    
                NetworkManager.getInstance().addToQueue(req);
            }
        });
            Command backBtn = Back.createBackBtn();
        this.getToolbar().addCommandToRightBar(Back.createBackBtn());
    }

}
