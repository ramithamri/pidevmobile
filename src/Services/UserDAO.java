/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import Entities.Conversation;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class UserDAO 
{
    private ConnectionRequest connectionRequest;
    public static Form listOfUsers;
    List<Conversation> conversationsList;
    Command cmd1, cmd2, cmd3, cmdBack;
    public UserDAO()
    {
        conversationsList = new ArrayList<>();
       
    }
    public void addUser(User user) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                InteractionDialog d = new InteractionDialog("Registration");
                TextArea popupBody = new TextArea("Registration successful");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                Button ok = new Button("OK");
                ok.addActionListener((ee) -> d.dispose());
                d.addComponent(BorderLayout.SOUTH, ok);
                d.show(0, 0, 0, 0);

            }
        };
        String url="http://localhost/pidevnameone/insertuser.php?name=" +
                 user.getName()+"&surname="+user.getSurname()+"&username="+user.getUsername()+"&email="+user.getEmail()
                +"&password="+user.getPass()+"&telephone="+user.getTelephone()+"&datebirth="+user.getDateBirth()
                +"&dateinscription="+user.getDateInscription()+"&address1="+user.getAddress1()+"&address2="
                +user.getAddress2()+"&codepostal="+user.getCodePostal()+"&gender="+user.getGender()
                +"&newsletter="+user.getNewsletter()+"&secretquestion="+user.getSecretQuestion()
                +"&secretanswer="+user.getSecretAnswer()
                +"&photo="+user.getPhoto();
        System.err.println(url);
        connectionRequest.setUrl(url);
        NetworkManager.getInstance().addToQueue(connectionRequest);    
    }
    
    public void login(User user) {
        connectionRequest = new ConnectionRequest() {
            List<User> books = new ArrayList<>();
            User userkh = new User();
            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                    for (Map<String, Object> obj : content) {
                        System.out.println((String) obj.get("username"));
                        userkh= new User((int) obj.get("id"),(String) obj.get("name"), (String) obj.get("surname") , (String) obj.get("gender") , (String) obj.get("dateBirth") , (String) obj.get("email") , 
            (String) obj.get("username") , (String) obj.get("pass") , (String) obj.get("telephone") , (String) obj.get("address1") , (String) obj.get("address2") ,
            (String) obj.get("codePostal") , (String) obj.get("photo") , (String) obj.get("dateInscription") , (String) obj.get("newsletter") , (String) obj.get("idCompany") , 
            (String) obj.get("latitude") , (String) obj.get("longitude") , (String) obj.get("secretQuestion") , (String) obj.get("secretAnswer") );
                        books.add(userkh);
                        System.out.print(userkh.getUsername());
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                    System.out.println(user.getUsername());
                for (User obj : books) {
                    if (user.getUsername().equals(obj.getUsername()) && user.getPass().equals(obj.getPass())) {  
                        System.out.println(obj.getId());
                        User.setCurrentId(obj.getId());
                    
                    }
                }

            }
        };
        connectionRequest.setUrl("http://localhost/pidevnameone/login.php?username="+user.getUsername()+"&password="+user.getPass());
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
    public void deactivateAccount(User b) {   // remove book by title
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                InteractionDialog d = new InteractionDialog("Disabling account");
                TextArea popupBody = new TextArea("User account successfully disabled");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                Button ok = new Button("OK");
                ok.addActionListener((ee) -> d.dispose());
                d.addComponent(BorderLayout.SOUTH, ok);
                d.show(0, 0, 0, 0);
            }
        };
        connectionRequest.setUrl("http://localhost/pidevnameone/remove.php?username=" + b.getUsername());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void GetAllUsers(User session_user) 
    {
        connectionRequest = new ConnectionRequest() {
            List<User> users = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    users.clear();

                    for (Map<String, Object> obj : content) {
                        users.add(new User(Integer.parseInt((String) obj.get("id")),(String) obj.get("name"), (String) obj.get("surname") , (String) obj.get("gender") , (String) obj.get("dateBirth") , (String) obj.get("email") , 
            (String) obj.get("username") , (String) obj.get("pass") , (String) obj.get("telephone") , (String) obj.get("address1") , (String) obj.get("address2") ,
            (String) obj.get("codePostal") , (String) obj.get("photo") , (String) obj.get("dateInscription") , (String) obj.get("newsletter") , (String) obj.get("idCompany") , 
            (String) obj.get("latitude") , (String) obj.get("longitude") , (String) obj.get("secretQuestion") , (String) obj.get("secretAnswer") )              
                    );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfUsers = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                
                for (User l : users) 
                {
                      
                    libsNoms.add(l.getName()+' '+l.getSurname());
                }
                
                DefaultListModel<String> listModel = new DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) 
                    {
                        User currentBook = users.get(uiLibsList.getCurrentSelected());
                         MessageService mdao = new MessageService();
                        listOfUsers.removeAll();
                           System.out.println(currentBook.getId());
                           MessageService.messages.removeAll();
                           MessageService.conversations.removeAll();
                           mdao.NewConversation(currentBook.getId(), session_user,currentBook.getName()+' '+currentBook.getSurname());
                        
                    }
                });
                listOfUsers.setLayout(new BorderLayout());
                listOfUsers.add(BorderLayout.NORTH, uiLibsList);
                MessageService.conversations.removeAll();
                MessageService.messages.removeAll();
                listOfUsers.show();
            }
        };
        String url = "http://localhost/PidevMobile/scripts/ramy/getallusers.php?id=" + session_user.getId();
        url=url.replace(' ','+');
        connectionRequest.setUrl(url);
        
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

 List<Object> feedList;
 public void loadConversations(User user)
    {           
        if (!conversationsList.isEmpty())
        {
         conversationsList.clear();   
        }
        
            connectionRequest = new ConnectionRequest() 
            {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String,Object> data = json.parseJSON(reader);

                  List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    /*conversationsList = new ArrayList<>();*/
                   for (Map<String, Object> obj : content) {

                       Conversation conv = new Conversation(Integer.parseInt((String) obj.get("id")), Integer.parseInt((String) obj.get("userOneId")), 
                       Integer.parseInt((String) obj.get("userTwoId")), (String) obj.get("date"), Integer.parseInt((String) obj.get("status")) );
                       conversationsList.add(conv);
                   }
                    } catch (IOException err) {
                    Log.e(err);
                }
                System.out.println(conversationsList.size());
                 
                
            }

            @Override
            protected void postResponse() 
            {    
                
                            UserDAO udao = new UserDAO();
  


        cmd1=new Command("Hall");
        cmd2=new Command("New Conversation");
        cmd3= new Command ("Find User");
        cmdBack=new Command("Back");
        MessageService.conversations.setTitle("Messages");
        
       
        MessageService.conversations.getToolbar().addCommandToSideMenu(cmd1);
        MessageService.conversations.getToolbar().addCommandToSideMenu(cmd2);
                MessageService.conversations.getToolbar().addCommandToOverflowMenu(cmd3);

       
        MessageService.conversations.addCommandListener(ev->{
        if(ev.getCommand()==cmd2)
        { 
            MessageService.conversations.removeAll();
            udao.GetAllUsers(user);
                 
        }
        if(ev.getCommand()==cmd1)
        {
                
        }
       
        
        });
        


              if (!conversationsList.isEmpty())
              {
                  for(Conversation c : conversationsList)
                {
                    if(c.getUserOneId() == user.getId()){
                      
                     getUserById(c.getUserTwoId(), c, user);
                    
                    }
                    if(c.getUserTwoId() == user.getId())
                    {
                        getUserById(c.getUserOneId(), c, user);
                       
                    }           
                }
              }
              else
              {
                  
                    
                    Label content = new Label();
                    content.setText("you have no Conversation");
                    MessageService.conversations.add(content);


              }
                
                 MessageService.conversations.show();
            }

        };
         
         connectionRequest.setUrl("http://localhost/PidevMobile/scripts/ramy/show_conversations.php?id="+user.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
                   

    }
 User userAux;
 public void getUserById(int idd, Conversation c, User session_user)
{
       connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException 
            {
                
                 JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String,Object> data = json.parseJSON(reader);

                    List<Map<String,Object>>dat = (List<Map<String,Object>>)data.get("root");


                        for (Map<String, Object> obj : dat) 
                        {
                                     userAux= new User(Integer.parseInt((String)obj.get("id")), (String) obj.get("email"),(String) obj.get("pass")
                                      ,(String) obj.get("name"), (String) obj.get("surname") ,(String) obj.get("type"));
                            }

                    } catch (IOException err) {
                    Log.e(err);
                }

                 MessageService mdao = new MessageService();
                 MultiButton mb = new MultiButton(""+userAux.getName()+" "+userAux.getSurname());
                 mb.setUIID("MessagesMultiButton");
                 Style s = new Style();
                 ColorUtil cu = new ColorUtil ();
                 
                 s.setBgColor(cu.LTGRAY);
                 
                 
                 
                 if (c.getStatus()==0)
                 {
                     mb.setSelectedStyle(s);
                 mb.setUnselectedStyle(s);
                 
                 }
                 
                       //mb.setTextLine2("#"+c.getConversationId() );
                       String name = userAux.getName()+" "+userAux.getSurname();
                       mb.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) 
                     {
                         MessageService.messages.removeAll();
                         int status=1;
                         MessageService.modif_Conversation(c.getId(),status);
                         mdao.showMessages(c.getConversationId(),idd, session_user, name);
                     }
                 });                      
                      
                       
                       
        
        
                       MessageService.conversations.add(mb);                      
            }

            @Override
            protected void postResponse() 
            {
                       

            }

        };
        String url = "http://localhost/PidevMobile/scripts/ramy/find_user_by_id.php?id="+idd;
            System.out.println(url);
            url=url.replace(' ','+');
           
            connectionRequest.setUrl(url);
            NetworkManager.getInstance().addToQueue(connectionRequest);
            
            
                


                             
}

   

    
}

