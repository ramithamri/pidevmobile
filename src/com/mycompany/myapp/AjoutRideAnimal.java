/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author chams
 */
public class AjoutRideAnimal{
    //     private Form current;
    private Resources theme;
    Form fRideAnimal;

    public AjoutRideAnimal(Resources theme) {
      
        
        UIBuilder ub = new UIBuilder();
        UIBuilder.registerCustomComponent("Picker", Picker.class);
        UIBuilder.registerCustomComponent("AutoCompleteTextField", AutoCompleteTextField.class);
        
        fRideAnimal = ub.createContainer(theme, "AjoutRideAnimal").getComponentForm();;
        
        Button BtnOk = (Button) ub.findByName("valider", fRideAnimal);
        
        AutoCompleteTextField VilledeDepart = (AutoCompleteTextField) ub.findByName("Villededepart", fRideAnimal);
        AutoCompleteTextField VilledeDest = (AutoCompleteTextField) ub.findByName("Villededest", fRideAnimal);
                AutoCompleteTextField LTypes = (AutoCompleteTextField) ub.findByName("TypeAnimal", fRideAnimal);

        Picker date = (Picker) ub.findByName("Date", fRideAnimal);
        TextField FSource = (TextField) ub.findByName("pointDepart", fRideAnimal);
        TextField FDestination = (TextField) ub.findByName("pointdedest", fRideAnimal);
               
        TextField FExtra = (TextField) ub.findByName("FExtra", fRideAnimal);
       

       //fAnnonce.show();
        
        
        BtnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();   //citySource	, placeSource, cityDestination, placeDestination,speciesAnimal, specialNeeds,dateSource
                req.setUrl("http://localhost/PidevMobile/scripts/aymen/insertRideAnimal.php?villesource=" + VilledeDepart.getText() +"&placesource=" +FSource.getText() + "&villedestination="+ VilledeDest.getText() +"&placedestination=" + FDestination.getText() + "&typeAnimal=" + LTypes.getText()+ "&extraInfo="+FExtra.getText()+"&date="+date.getText()+"&date="+date.getText()+"");  

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Votre Demande à été ajoutée", "Ok", null);
                            
                                                 } 
                        else {
                            Dialog.show("Erreur", "Erreur lors de l'ajout, Veuillez vérifier votre connexion", "Retour", null);
                             }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });
       
    }

    public Form getfRideAnimal() {
        return fRideAnimal;
    }
    
    
    
}
