/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.mycompany.myapp.ReviewsService.listOfReviews;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SALMA
 */
public class ServiceRide {
    public static int x ; 
     private ConnectionRequest connectionRequest;
     
   
      public void addRide(RidePassenger ride){
          
          connectionRequest = new ConnectionRequest(){
              @Override
              protected void postResponse() {
                  Dialog d = new Dialog("Add Your Ride");
                  
                 
                  d.setLayout(new BorderLayout());
                   d.show("Ride Added", "your Ride is now added ", "Ok", null);
                     new  ServiceRide().findAllRides();
                
              }
          };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/insertRide.php?price=" + ride.getPrice()+ "&citySource=" + ride.getCitySource()+"&cityDestination="+ride.getCityDestination());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
      public void findAllRides() {
        connectionRequest = new ConnectionRequest() {
            List<RidePassenger> Ride = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("ridepassenger");
                    Ride.clear();

                    for (Map<String, Object> obj : content) {
                        Ride.add(new RidePassenger(Integer.parseInt((String) obj.get("id")),Integer.parseInt((String) obj.get("price")), (String) obj.get("citySource"), (String) obj.get("cityDestination"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }

            }

            @Override
            protected void postResponse() {
                listOfReviews = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for (RidePassenger l : Ride) {
                    libsNoms.add(l.getCitySource()+"  =>  "+l.getCityDestination());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);

                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        RidePassenger current = Ride.get(uiLibsList.getCurrentSelected());
                        new ViewRide(current.getId(),current.getPrice(), current.getCitySource(), current.getCityDestination()).show();
           x=current.getId();
                        System.out.println(x);
                    }
                });

                MyApplication a = new MyApplication();
                listOfReviews.setLayout(new BorderLayout()); //layout Manager 
                listOfReviews.add(BorderLayout.NORTH, uiLibsList).getStyle().setBgColor(0x45874586);
                listOfReviews.getToolbar().addCommandToRightBar("back", null, e -> a.start());
                listOfReviews.show();

            }
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/selectRide.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
       public void removeRide(RidePassenger R){   // remove
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("");
           
            d.setLayout(new BorderLayout());
           if(d.show("Ride", "your Ride was deleted", "Ok", null)){
               findAllRides();
           }
            }           
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/removeRide.php?id=" + x);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
        public void updateRide(RidePassenger R){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
         
                      Dialog d = new Dialog("");
           
            d.setLayout(new BorderLayout());
           if(d.show("Ride", "your Ride was updated", "Ok", null)){
               findAllRides();
           }
            }           
        };
            
        
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/update.php?price="+R.getPrice()+"&citySource="+R.getCitySource()+
                                "&cityDestination="+R.getCityDestination()+"&id="+x);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}
