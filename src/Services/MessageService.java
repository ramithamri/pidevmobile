/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import Entities.Messages;
import Entities.User;
import Entities.Conversation;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lowkeyz
 */
public class MessageService {
    private Form current;
    private Resources theme;
    public static Form conversations;
    public static Form messages;
    
    public User user;
    public static int sended=0;
    
    public MessageService()
    {
        
       
    }
    public MessageService(User u)
    {
        this.user = u;
       
    }
    
    public void init() 
    {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
        ConnectionRequest con;
    List<Messages> messagesList;
     List<Conversation> NouvConversation;

     

     
    
    public void showMessages(int convoId, int userId, User session_user, String name)
    {
       MessageService.messages.removeAllCommands();
       
       MessageService.messages.getToolbar().revalidate();
       UserDAO cdao = new UserDAO();
       String n = Integer.toString(convoId);
       Command cmdDelete=new Command("Delete");
       Command cmdEmail=new Command ("e-mail");
       TextArea content = new TextArea();
       Button send = new Button("Send");
                MessageService.messages.getToolbar().setUIID("Toolbar2");
                
                MessageService.messages.getToolbar().addCommandToOverflowMenu(cmdEmail);
                MessageService.messages.addCommandListener(ev->{
                                            if(ev.getCommand()==cmdEmail)
                                            {
                                                MessageService.messages.getToolbar().removeOverflowCommand(cmdEmail);
                                                       MessageService.messages.getToolbar().revalidate();

                                                MessageService.messages.removeAll();
                                                MessageService.sendEmail(convoId,userId,session_user, name);
                                                ;
                                            }
                                            });
                MessageService.messages.getToolbar().addCommandToRightBar(cmdDelete);
                MessageService.messages.addCommandListener(e->
                                              {
                                              if(e.getCommand()==cmdDelete)
                                              {
                                              
                                              
                                              if (Dialog.show("Confirm", "Do you want to Delete this Conversation permanantly?", "OK", "Cancel")) 
                                              {
                                              MessageService smv = new MessageService();
                                              
                                              System.out.println("id de la conversation");
                                              System.out.println(convoId);
                                              smv.deleteConvo(convoId,session_user);
                                              MessageService.messages.getToolbar().removeOverflowCommand(cmdEmail);
                                                     MessageService.messages.getToolbar().revalidate();

                                              MessageService.conversations.removeAll();
                                              cdao.loadConversations(session_user);
                                              }
                                              
                                              }
                                              
                                               });
                                            MessageService.messages.getToolbar().setUIID("Toolbar2");
                                            Command cmdBack=new Command("Back");
                                            MessageService.messages.getToolbar().addCommandToLeftBar(cmdBack);
                                            MessageService.messages.addCommandListener(ev->{
                                            if(ev.getCommand()==cmdBack)
                                            {
                                                if (!content.getText().equals(""))
                                                {
                                                 if (Dialog.show("Confirm", "Do you want to leave this Conversation before sending your message?", "OK", "Cancel")) 
                                              {
                                                MessageService.messages.getToolbar().removeOverflowCommand(cmdEmail);
                                                       MessageService.messages.getToolbar().revalidate();

                                                MessageService.conversations.removeAll();
                                                
                                                cdao.loadConversations(session_user);
                                              }
                                                }
                                                else
                                                {
                                                MessageService.messages.getToolbar().removeOverflowCommand(cmdEmail);
                                                       MessageService.messages.getToolbar().revalidate();

                                                MessageService.conversations.removeAll();
                                                
                                                cdao.loadConversations(session_user);
                                                }
                                            }
                                            });
         
         con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException 
            {
                
                
                
                 JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String,Object> data = json.parseJSON(reader);

                    List<Map<String,Object>>dat = (List<Map<String,Object>>)data.get("root");

                    messagesList = new ArrayList<Messages>();
                        for (Map<String, Object> obj : dat) 
                        {
                                     Messages message= new Messages(Integer.parseInt((String)obj.get("id")), Integer.parseInt((String) obj.get("senderUserId")),  Integer.parseInt((String) obj.get("conversationId")) 
                                     ,(String) obj.get("messageContent") ,(String) obj.get("time") );
                                     messagesList.add(message);
                            }

                    } catch (IOException err) {
                    Log.e(err);
                }
                               Form F = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                               F.setScrollable(true);
                               
                              //  Form F = new Form(new FlowLayout());

                for(Messages m : messagesList)
                {
                      TextArea msg = new TextArea(m.getMessageContent());
                      msg.setEditable(false);
                        msg.setFocusable(false);
                      
                      if(m.getSenderUserId() == session_user.getId())
                      {
                          
                          msg.setUIID("outgoing_message");
                          msg.setAlignment(Component.RIGHT);
                          
                      } else 
                      {
                           msg.setUIID("incoming_message");
                           msg.setAlignment(Component.LEFT);
                      }
                       F.add(msg);
                }
             
             send.addActionListener(e ->
             {
               if (content.getText().equals(""))
               {
              Dialog.show("Information", "Your message is empty", "OK", "Cancel");
    
               }
               else
               {
               MessageService.messages.getToolbar().removeOverflowCommand(cmdEmail);
                                                       MessageService.messages.getToolbar().revalidate();
               MessageService.sendMessage(session_user.getId(),convoId,content.getText(),userId,session_user,name);
               }
               
             });
                                            
             F.add(content);
             F.add(send);
             MessageService.messages.add(F);
             MessageService.messages.setTitle(name);
                                            
                                            
                                            
            }
             @Override
            protected void postResponse() 
            { 
                
             
            }

        };
        String url = "http://localhost/PidevMobile/scripts/ramy/show_messages.php?id="+convoId;
            System.out.println(url);
            url=url.replace(' ','+');
           
            con.setUrl(url);
            NetworkManager.getInstance().addToQueue(con);
            
           
                                             MessageService.messages.show();
    }
    public void deleteConvo(int id,User session_user)
     {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(id);
        String url = "http://localhost/PidevMobile/scripts/ramy/delete_convo.php?id="+id;
            System.out.println(url);
            url=url.replace(' ','+');
           
            con.setUrl(url);
            NetworkManager.getInstance().addToQueue(con);
            
            
            
            
            
            
    }
     public void NewConversation(int user_id, User session_user,String name)
    {
        
       ConnectionRequest con = new ConnectionRequest();
           String url = "http://localhost/PidevMobile/scripts/ramy/new_conversation.php?senderId="+session_user.getId()+"&receiverId="+user_id;
            System.out.println(url);
            url=url.replace(' ','+');
           
            con.setUrl(url);
            NetworkManager.getInstance().addToQueue(con);
            ConnectionRequest con2 = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException 
            {
               
                
                 JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String,Object> data = json.parseJSON(reader);

                    List<Map<String,Object>>dat = (List<Map<String,Object>>)data.get("root");

                    NouvConversation = new ArrayList<Conversation>();
                        for (Map<String, Object> obj : dat) 
                        {
                            Conversation c = new Conversation();
                                     c.setId(Integer.parseInt((String)obj.get("id")));
                                     NouvConversation.add(c);
                         }

                    } catch (IOException err) {
                    Log.e(err);
                }
                               Form F = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                               F.setScrollable(true);
                               
                              //  Form F = new Form(new FlowLayout());

               
             TextArea content = new TextArea();
             Button send = new Button("Send");
             send.addActionListener(e ->
             {
               if (content.getText().equals(""))
               {
              Dialog.show("Information", "Your message is empty", "OK", "Cancel");
    
               }
               else
               {
               MessageService.sendMessage(session_user.getId(),NouvConversation.get(0).getId(),content.getText(),user_id,session_user,name);
               MessageService.messages.removeAll();
               }
               
               
             });
                                            
                                          
                                           
                                            MessageService.messages.getToolbar().setUIID("Toolbar2");
                                            Command cmdBack=new Command("Back");
                                            MessageService.messages.getToolbar().addCommandToLeftBar(cmdBack);
                                            MessageService.messages.addCommandListener(ev->{
                                            if(ev.getCommand()==cmdBack)
                                            {
                                                        MessageService.conversations.removeAll();
                                                UserDAO cdao = new UserDAO();
                                                cdao.loadConversations(session_user);
                                            }
                                            
                                            });
                                            
                                            
                                            
                                            F.add(content);
                                            F.add(send);
                                            MessageService.messages.add(F);
                                            MessageService.messages.setTitle(name);
                                            MessageService.messages.show();
                                            
                                            
            }
             @Override
            protected void postResponse() 
            { 
                
             
                     

            }
            };
            String url2 = "http://localhost/PidevMobile/scripts/ramy/get_new_conversation.php?senderId="+session_user.getId()+"&receiverId="+user_id;
            System.out.println(url2);
            url2=url2.replace(' ','+');
           
            con2.setUrl(url2);
            NetworkManager.getInstance().addToQueue(con2);
            
        

        
    }
    
    public static void sendMessage(int senderId, int convoId, String content,int userId,User session_user, String name)
    {
                        ConnectionRequest con = new ConnectionRequest();

           String url = "http://localhost/PidevMobile/scripts/ramy/send_message.php?senderId="+senderId+"&convoId="+convoId+"&content="+content;
            System.out.println(url);
            url=url.replace(' ','+');
            int status=0;
            MessageService.modif_Conversation(convoId, status);
            con.setUrl(url);
            NetworkManager.getInstance().addToQueue(con);
            MessageService serv = new MessageService();
            MessageService.messages.removeAll();
            serv.showMessages(convoId,userId,session_user,name);
          
    }
    public static void sendEmail(int convoId,int userId,User session_user,String name)
    {
          MessageService.sended=0;
           User User2 = new User();
           MessageService.messages.removeAllCommands();
           MessageService.messages.getToolbar().setUIID("Toolbar2");
                                            Command cmdBack=new Command("Back");
                                            MessageService.messages.getToolbar().addCommandToLeftBar(cmdBack);
                                            MessageService.messages.addCommandListener(ev->{
                                            if(ev.getCommand()==cmdBack)
                                            {
                                                if (MessageService.sended==0)
                                                {
                                                 if (Dialog.show("Confirm", "Do you want to leave this page without sending your mail?", "OK", "Cancel")) 
                                              {
                                                MessageService serv = new MessageService();
                                                MessageService.messages.removeAll();
                                                serv.showMessages(convoId,userId,session_user,name);
                                              }
                                                }
                                                else
                                                {
                                                MessageService serv = new MessageService();
                                                MessageService.messages.removeAll();
                                                serv.showMessages(convoId,userId,session_user,name);
                                                }
                                            }
                                            });
           ConnectionRequest con = new ConnectionRequest()
           {
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
                           
                                     User2.setEmail((String)obj.get("email"));
                                     
                         }

                    } catch (IOException err) {
                    Log.e(err);
                    
             
                }
                Form mail_Form = new Form(new BoxLayout(BoxLayout.Y_AXIS));

                TextArea receiver_mail = new TextArea();
             receiver_mail.setEditable(false);
             TextArea objet_mail = new TextArea();
             TextArea contenu_mail = new TextArea();
             objet_mail.setHint("object");
             contenu_mail.setHint("your message");
             Button send_mail = new Button("Send Mail");
             
             send_mail.addActionListener(e ->
             {
                 
               if (!objet_mail.getText().equals("")&& !contenu_mail.getText().equals(""))  
                     {
               ConnectionRequest con2 = new ConnectionRequest()
               {
                    @Override
            protected void readResponse(InputStream input) throws IOException 
            {
                String reponse="";
                   JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");

                    Map<String,Object> data = json.parseJSON(reader);

                    List<Map<String,Object>>dat = (List<Map<String,Object>>)data.get("root");

                    
                        for (Map<String, Object> obj : dat) 
                        {
                           
                                    reponse = ((String)obj.get("reponse"));
                                     
                         }

                    } catch (IOException err) {
                    Log.e(err);
                    
             
                }
                if (reponse.equals("Message has been sent"))
                {
                     
                         if(Dialog.show("Information", "Mail Succesfuly sent", "OK", "Cancel"))
                     {
                        
                          MessageService serv = new MessageService();
                          MessageService.messages.removeAll();
                           serv.showMessages(convoId,userId,session_user,name);
                     }
                }
                else if (reponse.equals("Message could not be sent."))
                {
                         Dialog.show("Warning", "Your mail wasn't sent!", "OK", "Cancel");
                }
            }
               @Override
            protected void postResponse() 
            { 
            
           

            }
               
               };

           String url1 = "http://localhost/PidevMobile/scripts/ramy/Send_mail.php?receiver="+User2.getEmail()+"&objet="+objet_mail.getText()+"&contenu="+contenu_mail.getText();
            System.out.println(url1);
            url1=url1.replace(' ','+');
            
            con2.setUrl(url1);
            NetworkManager.getInstance().addToQueue(con2);
            }
               else if (objet_mail.getText().equals(""))   
                     {
                         Dialog.show("Warning", "Please set an object to your mail", "OK", "Cancel");
                     }
               else if (contenu_mail.getText().equals(""))
                     {
                         Dialog.show("Warning", "Your mail has no content", "OK", "Cancel");
                     }
             });
            
            receiver_mail.setText(User2.getEmail());
            mail_Form.add(receiver_mail);
            mail_Form.add(objet_mail);
            mail_Form.add(contenu_mail);
            mail_Form.add(send_mail);
            MessageService.messages.add(mail_Form);
            }
              @Override
            protected void postResponse() 
            { 
            
           

            }
            
           };
             
             String url = "http://localhost/PidevMobile/scripts/ramy/find_user_by_id.php?id="+userId;
            System.out.println(url);
            url=url.replace(' ','+');
           
            con.setUrl(url);
            NetworkManager.getInstance().addToQueue(con);
          
    }
    

    public void showConversations() 
    {
        
        if(current != null)
        {
            current.show();
            return;
        }

        UIBuilder ui = new UIBuilder();
        
        Container cnt1 = ui.createContainer(theme, "conversations");
        conversations = (Form) cnt1;
       // conversations.setTitle("Messages"); 
       
        Container cnt2 = ui.createContainer(theme, "messages");
        messages = (Form) cnt2;
        
       messages.setScrollableY(false);
        
        
       
       
       UserDAO cdao = new UserDAO();
       cdao.loadConversations(user);
        MessageService.conversations.getToolbar().setUIID("Toolbar2");
        
         
                
                        
    }
        public static void modif_Conversation(int idconv,int status)
        {
             ConnectionRequest con = new ConnectionRequest();
            String url3 = "http://localhost/PidevMobile/scripts/ramy/modif_conversation.php?id="+idconv+"&status="+status;
            System.out.println(url3);
            url3=url3.replace(' ','+');
           
            con.setUrl(url3);
            NetworkManager.getInstance().addToQueue(con);
        }
    

    
}
