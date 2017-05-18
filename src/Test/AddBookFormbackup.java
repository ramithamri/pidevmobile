/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controllers.ComplaintController;
import Entities.Complaint;
import Tools.OtherTools;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amal
 */
public class AddBookFormbackup extends Form {
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField titleTf,authorTf,categoryTf,isbnTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    
   public AddBookFormbackup(){
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add a new book");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Title:");
        titleTf = new TextField(""); 
        l3 = new Label("Author:");
        authorTf = new TextField("");
        l4 = new Label("Category:");
        categoryTf= new TextField("");
        l5 = new Label("ISBN:");
        isbnTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        OtherTools.setLabelStyle(l2);
        mainContainer.add(l2);
        OtherTools.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(titleTf);
        mainContainer.add(authorTf);
        OtherTools.setLabelStyle(l4);
        mainContainer.add(l4);
        OtherTools.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(categoryTf);
        mainContainer.add(isbnTf);
        mainContainer.add(addBtn);
        backBtn = OtherTools.createBackBtn(); 
        mainContainer.add(backBtn);
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Complaint typedBook = new Complaint(authorTf.getText(),titleTf.getText(),categoryTf.getText(),isbnTf.getText());
            new  ComplaintController().addComplaint(typedBook);
            });
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
