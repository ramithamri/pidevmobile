/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author Deathscythvi
 */
public class Statics {
  

    
    public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
     public static Command createBackBtn(){
         Command b = new Command("back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
             //MyApplication.hi.show();
                MyApplication mp = new MyApplication() ;
                mp.init(evt);
                mp.start();
            }
        };
       
         return b;
     }
    

    
}
