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
import static com.mycompany.myapp.GoogleMap.x;
import static com.mycompany.myapp.GoogleMap.y;




public class Search extends Form{
     private final Container mainContainer;
      

  
   
public Search(){
   
        this.setLayout(new BorderLayout());
         mainContainer = new Container();
        TextField tfsearch = new TextField();
       
        

        mainContainer.add(tfsearch);
      
        

        Button btnOk = new Button("search");
               Command backBtn = Statics.createBackBtn(); 
          this.getToolbar().addCommandToRightBar(Statics.createBackBtn());
          

        mainContainer.add(btnOk);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

               ridedriverservices s=new ridedriverservices();
               s.searchride(tfsearch.getText());
    }
        });
        this.add(BorderLayout.NORTH, mainContainer);
                }
}

  


                

                
