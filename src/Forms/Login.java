/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Controllers.UserController;
import Entities.SimpleUser;
import Tools.OtherTools;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;

/**
 *
 * @author Admin
 */
public class Login extends Form {
    
    private final Label login,password;
    private final TextField loginTf,passwordTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    
   public Login(){
       
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

        login = new Label("Username");
        login.setAlignment(CENTER);
        login.getUnselectedStyle().setFgColor(-16777216);
        Font login_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        login.getUnselectedStyle().setFont(login_font);
        loginTf= new TextField("");
        mainContainer.add(login);
        mainContainer.add(loginTf);
        
        password = new Label("Password:");
        password.setAlignment(CENTER);
        password.getUnselectedStyle().setFgColor(-16777216);
        password.getUnselectedStyle().setFont(login_font);
        passwordTf= new TextField("");
        passwordTf.setConstraint(com.codename1.ui.TextArea.PASSWORD);
 
        mainContainer.add(password);
        mainContainer.add(passwordTf);
        
        
        
        //login.getUnselectedStyle().setAlignment(Component.CENTER);
        addBtn= new Button("Login");
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            SimpleUser user= new SimpleUser();
            user.setUsername(loginTf.getText());
            user.setPass(passwordTf.getText());
            new  UserController().login(user);
        });
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(addBtn); 
        backBtn = OtherTools.createBackBtnRegister();
        mainContainer.add(backBtn);
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
