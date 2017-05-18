/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Controllers.ComplaintController;
import Entities.Complaint;
import Entities.SimpleUser;
import Tools.OtherTools;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;

/**
 * GUI builder created Form
 *
 * @author Admin
 */
public class AddComplaintForm extends com.codename1.ui.Form {

    private final Label l1,l2,l3;
    private final TextField  content;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    private final Picker type;

    public AddComplaintForm() {        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
         
        TableLayout tl;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
}
tl.setGrowHorizontally(true);
mainContainer.setLayout(tl);

        
        

l1 = new Label("Add complaint");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Type:");
        type = new Picker();
        type.setType(Display.PICKER_TYPE_STRINGS);
        type.setStrings("User disrespectful", "User smoking", "User late",
        "User with animals" , "User harassment");
        type.setSelectedString("User disrespectful");

        l3 = new Label("Content:");
        content = new TextField("", "First Name", 20, TextArea.ANY);
        content.setLinesToScroll(1);
        content.setSingleLineTextArea(false);
            addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);

        OtherTools.setLabelStyle(l2);
        OtherTools.setLabelStyle(l3);
        backBtn = OtherTools.createBackBtn(); 
        Validator v = new Validator();
        v.addConstraint(content, new LengthConstraint(4));
                v.addSubmitButtons(addBtn);

        mainContainer.add(l1).add(l2).add(type)
                .add(l3).add(content).add(addBtn).add(backBtn);

        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            Complaint typedBook = new Complaint(type.getText(),content.getText(),OtherTools.getCurrentTimeStamp(),SimpleUser.getCurrentId());
            new  ComplaintController().addComplaint(typedBook);
            }); 
        this.add(BorderLayout.NORTH, mainContainer);
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
