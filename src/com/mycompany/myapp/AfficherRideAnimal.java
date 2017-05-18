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



import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class AfficherRideAnimal {
    
    private Resources theme;
     Form f1;
     
     int mem=2;
    
   public AfficherRideAnimal(Resources theme) {
        String url="http://localhost/PidevMobile/scripts/aymen/selectRideAnimal.php?idUser="+mem;
        ConnectionRequest request=  new ConnectionRequest(url);
        UIBuilder ui = new UIBuilder();
        f1 = ui.createContainer(theme, "AffRide").getComponentForm();
       
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
        
        //Button Mapsg = new Button("Voir itinéraire");
         Label sep = new Label("------------------------------------");
        cnt1.add(depdest);
        cnt1.add(appro);
        cnt1.add(datetarif);
       // cnt1.add(Mapsg);
        cnt1.add(sep);
        
        
     
        
      
        
        
        
   
        
        f1.add(cnt1);
       // f1.show();
         /*
         Mapsg.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                           
               //Form hi = new Form("Hi World");
        
        Form f2 = new Form("Itinéraire");
        //Container cnt100 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        WebBrowser testweb = new WebBrowser();
        Button retour = new Button("<Retour");
        //retour.setIcon("a.png");
        testweb.setURL("http://localhost/linkar/Web/app_dev.php/mapsMobile/"+ride.get("id"));
        f2.addComponent(retour);
        f2.addComponent(testweb);
        f2.show();
        
        
        retour.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                           
          
        f1.show();
        
                NetworkManager.getInstance().addToQueue(request);
                        }
                    });
        
                NetworkManager.getInstance().addToQueue(request);
                        }
                    });
          
         */
         
        
        
                    
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
                //Logger.getLogger(ParsingTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        
        
    }

    public Form getF1() {
        return f1;
    }

   
     
    
    
}

