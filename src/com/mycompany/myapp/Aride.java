
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import static com.mycompany.myapp.ridedriverservices.x;

/**
 *
 * @author Deathscythvi
 */
public class Aride extends Form  {

    private final Label l1,l2,l3,l4,l5;
    private final TextField titleTf,authorTf,categoryTf,isbnTf;
    private final Container mainContainer;
    private final Button editBtn,removeBtn;
    private Ridedriver currentride;
    
    public Aride(int id,Double price,String citysource,String citydestination,int nbrPlaces){
         
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("ridedriver"+id);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Price:");
        titleTf = new TextField(price+"");
        l3 = new Label("Citysource");
        authorTf = new TextField(citysource);
        l4 = new Label("Citydestination");
        categoryTf= new TextField(citydestination);
        l5 = new Label("nbrplaces");
        isbnTf= new TextField(nbrPlaces+"");
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        mainContainer.add(l2);
        mainContainer.add(l3);
        mainContainer.add(titleTf);
        mainContainer.add(authorTf);
        mainContainer.add(l4);
        mainContainer.add(l5);
        mainContainer.add(categoryTf);
        mainContainer.add(isbnTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
       Command backBtn = Statics.createBackBtn(); 
          this.getToolbar().addCommandToRightBar(Statics.createBackBtn());
       
       
   
       
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
             currentride = new Ridedriver(x,Double.parseDouble(titleTf.getText()),authorTf.getText(),categoryTf.getText(),Integer.parseInt(isbnTf.getText()));
            new ridedriverservices().updateBook(currentride);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ridedriverservices().removeride();
                System.out.println(x);
            }
        });
        this.add(BorderLayout.NORTH, mainContainer);
    }
}



