package com.mycompany.myapp;



import com.codename1.components.ToastBar;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;


public class MyApplication1 extends Form {
     private Form current;
    private Resources theme;
    private final Button go;
    
    
     

  
    
    public MyApplication1() {
        go=new Button("Go");
        
        
        
        final GoogleMap map = new GoogleMap();
        this.setLayout(new BorderLayout());
        
        final TextField start = new TextField();
        start.setHint("Start location");
        
        
        
        final TextField end = new TextField();
        end.setHint("Destination");
        Container form = new Container();
        form.setLayout(new BorderLayout());
        form.addComponent(BorderLayout.NORTH, start);
        form.addComponent(BorderLayout.CENTER, end);
        form.addComponent(BorderLayout.SOUTH, go);
        
        Command backBtn = Statics.createBackBtn(); 
          this.getToolbar().addCommandToRightBar(Statics.createBackBtn());
        
        ActionListener routeListener = new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                if ( !"".equals(start.getText()) && !"".equals(end.getText())){
                    DirectionsRequest req = new DirectionsRequest();
                    req.setTravelMode(DirectionsRequest.TRAVEL_MODE_DRIVING);
                    req.setOriginName(start.getText());
                    req.setDestinationName(end.getText());
                    map.route(req, new DirectionsRouteListener(){

                        public void routeCalculated(DirectionsResult result) {
                            System.out.println("Successfully mapped route");
                           
                            
       
                                                  
                        }
                        
                    });
                }
            }
            
        };
         go.addActionListener((ActionListener) (ActionEvent evt) -> {
             if ((start.getText().equals(""))|| (end.getText().equals(""))) {
            go.addActionListener(e -> ToastBar.showErrorMessage("please Select source and destination"));}else{
            
              AddrideDriver addrideui = new AddrideDriver();
                addrideui.show();
             }
            });
        
        start.addActionListener(routeListener);
        end.addActionListener(routeListener);
        this.addComponent(BorderLayout.NORTH, form);
        this.addComponent(BorderLayout.CENTER, map);
        this.show();
       
    }


 }
