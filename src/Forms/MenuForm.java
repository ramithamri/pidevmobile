/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Controllers.ComplaintController;
import Forms.*;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;

/**
 *
 * @author Admin
 */
public class MenuForm extends com.codename1.ui.Form {
        private Button addBookBtn,listBooksBtn,exitBtn;
            private Container mainContainer;
                private Resources theme;


            public MenuForm (){
 
                        mainContainer = new Container();
         
        TableLayout tl;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
}
tl.setGrowHorizontally(true);
mainContainer.setLayout(tl);

exitBtn = new Button("Exit");
            addBookBtn = new Button("Add new complaint");
        addBookBtn.getUnselectedStyle().setFgColor(5542241);
        listBooksBtn = new Button("My complaints");
        listBooksBtn.getUnselectedStyle().setFgColor(5542241);
        this.setLayout(new BorderLayout());

//
//        mainContainer.setScrollableY(true);
//        mainContainer.setScrollVisible(true);

        
        listBooksBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ComplaintController().findAllComplaints();     
            }
        });
        addBookBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddComplaintForm().show();
            }
        });
        exitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                        Display.getInstance().exitApplication();
            }
        });
        
        mainContainer.add(addBookBtn).add(listBooksBtn).add(exitBtn);
                this.add(BorderLayout.NORTH, mainContainer);

            }

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
