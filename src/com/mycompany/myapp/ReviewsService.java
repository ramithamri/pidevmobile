/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entities.SimpleUser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.mycompany.myapp.AddReview.x;
import static com.mycompany.myapp.MyApplication.hi;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SALMA
 */
public class ReviewsService {

    private Button backBtn;

    private ConnectionRequest connectionRequest;
    public static Form listOfReviews;
    public static List<Double> Re;
    public static List<String> name;

    public void addReview(Reviews Review) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("Add to my book shelf");
                TextArea popupBody = new TextArea("Book successfully added");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.showDialog();
            }
        };
        SimpleUser.setCurrentId("56");
       // int iduser = Integer.parseInt(SimpleUser.getCurrentId());
          int iduser = 56;
          int id2 = 59;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/PidevMobile/scripts/salma/insert.php?rating=" + x + "&title=" + Review.getTitle() + "&content=" + Review.getContent() + "&idUser2=" +59 + "&idUser=" + iduser);

    }

    public void findAllBooks() {
        connectionRequest = new ConnectionRequest() {
            List<Reviews> Rev = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("reviews");
                    Rev.clear();

                    for (Map<String, Object> obj : content) {
                        Rev.add(new Reviews(Integer.parseInt((String) obj.get("rating")), (String) obj.get("title"), (String) obj.get("content"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }

            }

            @Override
            protected void postResponse() {
                listOfReviews = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for (Reviews l : Rev) {
                    libsNoms.add(l.getTitle());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);

                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Reviews current = Rev.get(uiLibsList.getCurrentSelected());
                        new Areviews(current.getRating(), current.getTitle(), current.getContent()).show();

                    }
                });

                MyApplication a = new MyApplication();
                listOfReviews.setLayout(new BorderLayout());
                listOfReviews.add(BorderLayout.NORTH, uiLibsList).getStyle().setBgColor(0x45874586);
                listOfReviews.getToolbar().addCommandToRightBar("Back", null, e -> a.start());
                listOfReviews.show();

            }
        };
       SimpleUser.setCurrentId("56");
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/select.php?user="+SimpleUser.getCurrentId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void removeBook(Reviews b) {   // remove review 
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Removed", "Review Succefully removed ", "ok ", null);
                new ReviewsService().findAllBooks();
            }
        };

        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/remove.php?title=" + b.getTitle());
        NetworkManager.getInstance().addToQueue(connectionRequest);
        MyApplication a = new MyApplication();
        hi.getToolbar().addCommandToRightBar("back", null, e -> a.start());
    }

    public List<Double> findAll() {
        connectionRequest = new ConnectionRequest() {
            List<Double> Rev = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("reviews");
                    Rev.clear();

                    for (Map<String, Object> obj : content) 
                    {
                        Rev.add(Double.parseDouble((String) obj.get("moy")));
                    }
                } catch (IOException err) {
                    Log.e(err);
                }

            }

            @Override
            protected void postResponse() {
                Re = Rev;

            }
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/stat.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        return Re;
    }

    public List<String> findByName() {
        connectionRequest = new ConnectionRequest() {
            List<String> e = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("user");
                    e.clear();

                    for (Map<String, Object> obj : content) {
                        e.add((String) obj.get("username"));
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                name = e;
                System.out.println(name);
            }
        };
        connectionRequest.setUrl("http://localhost/PidevMobile/scripts/salma/UserByName.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);

        return name;
    }
}
