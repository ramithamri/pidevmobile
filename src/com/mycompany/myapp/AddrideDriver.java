package com.mycompany.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import static com.mycompany.myapp.GoogleMap.x;
import static com.mycompany.myapp.GoogleMap.y;




public class AddrideDriver extends Form{
     private final Container mainContainer;
     public static String s;
      

  
   
public AddrideDriver(){
   
        this.setLayout(new BorderLayout());
         mainContainer = new Container();
        TextField tfPrice = new TextField("","Price");
        TextField tfCitysource = new TextField(x);
        TextField tfCitydestination = new TextField(y);
        tfCitysource.setEnabled(false);
        tfCitydestination.setEnabled(false);
        
        TextField tfNbplace = new TextField("", "place number");
        String phoneConstraint="^[0-9]";
        
    Validator v = new Validator();
    v.  addConstraint(tfPrice, new RegexConstraint(phoneConstraint,"Please enter a valid number")).
        addConstraint(tfNbplace, new RegexConstraint(phoneConstraint,"Please enter a valid number"))
            
           ;
    Button btnOk = new Button("Insert");
            
    v.addSubmitButtons(btnOk);
         

        mainContainer.add(tfPrice);
        mainContainer.add(tfCitysource);
        mainContainer.add(tfCitydestination);
        mainContainer.add(tfNbplace);
       
        
        
        

        
               Command backBtn = Statics.createBackBtn(); 
          this.getToolbar().addCommandToRightBar(Statics.createBackBtn());
          

        mainContainer.add(btnOk);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Ridedriver ridedriver=new Ridedriver(Double.parseDouble(tfPrice.getText()),tfCitysource.getText(),tfCitydestination.getText(),Integer.parseInt(tfNbplace.getText()));
                new ridedriverservices().addride(ridedriver);
    }
        });
        this.add(BorderLayout.NORTH, mainContainer);
                }
}

  


                

                
