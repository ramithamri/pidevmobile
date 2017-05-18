
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Deathscythvi
 */
public class ridedriverservices {
    public static Form listOfrides;
    public static int x;
    public static int y;
    
      private ConnectionRequest connectionRequest;
   public void afficheride(){
       
       connectionRequest = new ConnectionRequest() {
        List<Ridedriver> rides = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("ridedriver");
                    rides.clear();
                  
                    for (Map<String, Object> obj : content) {
                        rides.add(new Ridedriver(Integer.parseInt((String) obj.get("id")),Double.parseDouble((String) obj.get("price")),(String) obj.get("citysource"),(String) obj.get("citydestination"),Integer.parseInt((String) obj.get("nbrPlaces")))
                               
                        );
                       
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfrides = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Ridedriver l :rides){
                    libsNoms.add("From "+l.getCitysource()+" To "+l.getCitydestination());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Ridedriver currentBook = rides.get(uiLibsList.getCurrentSelected());
                        
                        new Aride(currentBook.getId(),currentBook.getPrice(),currentBook.getCitysource(),currentBook.getCitydestination(),currentBook.getNbrPlaces()).show();
                        x=currentBook.getId();
                    }
                    
                });
           
                listOfrides.setLayout(new BorderLayout());
                listOfrides.add(BorderLayout.NORTH,uiLibsList);
                Command backBtn = Statics.createBackBtn(); 
                listOfrides.getToolbar().addCommandToRightBar(Statics.createBackBtn());
                listOfrides.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/helmi/select.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void addride(Ridedriver ridedriver){
       
        ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/PidevMobile/scripts/helmi/insert.php?price=" + ridedriver.getPrice() + "&citysource=" + ridedriver.getCitysource() + "&citydestination=" + ridedriver.getCitydestination() +"&nbrPlaces=" + ridedriver.getNbrPlaces());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "add done", "Ok", null);
                            afficheride();
                        }else {
                            Dialog.show("error", "error while adding", "Ok", null);
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                
 
            }
     public void updateBook(Ridedriver ridedriver){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("ride updated");
               if(Dialog.show("Confirmation", "update done", "Ok", null)){
               afficheride();
               }
              
            }
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/helmi/update.php?price="+ridedriver.getPrice()+"&citysource="+ridedriver.getCitysource()+
                                "&citydestination="+ridedriver.getCitydestination()+"&nbrPlaces="+ridedriver.getNbrPlaces()+"&id="+ridedriver.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     public void removeride(){   // remove book by title
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Remove ride from database");
            TextArea popupBody = new TextArea("ride successfully removed");
            if(Dialog.show("Confirmation", "remove done", "Ok", null)){
            afficheride();
            }
            }           
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/helmi/remove.php?id="+x);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     public void searchride(String search){

       connectionRequest = new ConnectionRequest() {
          
        List<Ridedriver> rides = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("ridedriver");
                    rides.clear();
                  
                    for (Map<String, Object> obj : content) {
                        rides.add(new Ridedriver(Integer.parseInt((String) obj.get("id")),Double.parseDouble((String) obj.get("price")),(String) obj.get("citysource"),(String) obj.get("citydestination"),Integer.parseInt((String) obj.get("nbrPlaces")))
                               
                        );
                       
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                listOfrides = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Ridedriver l :rides){
                    libsNoms.add(l.getCitysource().toString());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Ridedriver currentBook = rides.get(uiLibsList.getCurrentSelected());
                        
                        new Abooking(currentBook.getId(),currentBook.getPrice(),currentBook.getCitysource(),currentBook.getCitydestination(),currentBook.getNbrPlaces()).show();
                        y=currentBook.getId();
                    }
                    
                });
           
                listOfrides.setLayout(new BorderLayout());
                listOfrides.add(BorderLayout.CENTER,uiLibsList);
                Command backBtn = Statics.createBackBtn(); 
                listOfrides.getToolbar().addCommandToRightBar(Statics.createBackBtn());
                listOfrides.show();             
            }
        };
  
             connectionRequest.setUrl("http://localhost/PidevMobile/scripts/helmi/search.php?citysource="+search);
       
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     public void addbooking(){
       
        ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/PidevMobile/scripts/helmi/insertbooking.php?idRidedriver=" + y + "&idUser=" + 3);

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "booking done", "Ok", null);
                            afficheride();
                        }else {
                            Dialog.show("jben", "jben", "jben", null);
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                
 
            }
        }

       
    
    

