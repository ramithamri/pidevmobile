/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.codename1.components.WebBrowser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chams
 */
public class AfficherMesRideAnimal {
    
    private Resources theme;
     Form f2;
     
     int mem=3;
    
   public AfficherMesRideAnimal(Resources theme) {
        String url="http://localhost/PidevMobile/scripts/aymen/selectMesRideAnimal.php?idUser="+mem;
        ConnectionRequest request=  new ConnectionRequest(url);
        UIBuilder ui = new UIBuilder();
        f2 = ui.createContainer(theme, "AffMesRide").getComponentForm();
       
       // WebBrowser testweb = new WebBrowser();
        request.addResponseListener((evt) -> {
            try {
                JSONParser parser= new JSONParser();
                Map<String, Object> result = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData())));
                List<Map<String,Object>> rides= (List<Map<String,Object>>) result.get("rideanimal");
                for (int i=0;i<rides.size();i++){
                    Map<String,Object> ride= rides.get(i);
                  
                    Container cnt1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label depdest = new Label("Depart: "+ride.get("citySource")+" Destination: "+ride.get("cityDestination"));
        Label appro = new Label("LextraInfo "+ride.get("specialNeeds"));
        Label datetarif = new Label("typeAnimal: "+ride.get("speciesAnimal"));
        
        Button delete = new Button("Delete");
         Label sep = new Label("------------------------------------");
        cnt1.add(depdest);
        cnt1.add(appro);
        cnt1.add(datetarif);
        cnt1.add(delete);
        cnt1.add(sep);
        
        
     
        
      delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                            ConnectionRequest request=  new ConnectionRequest();
                            request.setUrl("http://localhost/PidevMobile/scripts/aymen/DeleteRideAnimal.php?id="+ride.get("id"));
                            request.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Votre Demande à été supprimée", "Ok", null);
                            
                                                 } 
                        else {
                            Dialog.show("Erreur", "Erreur lors de la suppression, Veuillez vérifier votre connexion", "Retour", null);
                             }
                    }
                });

                NetworkManager.getInstance().addToQueue(request);
                        }
                    });
        
        
        
   
        
        f2.add(cnt1);
       // f1.show();
         
         
         
         
         
        
        
                    
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
                //Logger.getLogger(ParsingTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        
        
    }

    public Form getF2() {
        return f2;
    }

   
     
    
    
}

