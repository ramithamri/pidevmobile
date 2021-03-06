/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package Forms;

import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Oauth2;
import com.codename1.io.Storage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.LayeredLayout;
import Entities.SimpleUser;
import com.codename1.facebook.User;
import Controllers.UserController;
import Tools.OtherTools;
import java.io.IOException;

/**
 *
 * @author Chen
 */
public class LoginMain extends Form {

    private Form main;
    public static String TOKEN;

    public LoginMain(Form f) {
        super("Login");
        this.main = f;
        setLayout(new LayeredLayout());
        signIn(main);        
    }

    private static void signIn(final Form main) {
        FaceBookAccess.setClientId("132970916828080");
        FaceBookAccess.setClientSecret("6aaf4c8ea791f08ea15735eb647becfe");
        //   FaceBookAccess.setRedirectURI("http://www.codenameone.com/");
        FaceBookAccess.setPermissions(new String[]{"user_location", "user_photos", "friends_photos", "publish_stream", "read_stream", "user_relationships", "user_birthday",
            "friends_birthday", "friends_relationships", "read_mailbox", "user_events", "friends_events", "user_about_me"});

        FaceBookAccess.getInstance().showAuthentication(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                AccessToken token = (AccessToken) evt.getSource();

                String expires = Oauth2.getExpires();
                TOKEN = token.getToken();
                System.out.println("recived a token " + TOKEN + " which expires on " + expires);
                //store token for future queries.
                Storage.getInstance().writeObject("token", TOKEN);
                User me = new User();
                try {
                    me = FaceBookAccess.getInstance().getUser("me");
                    String localDate = OtherTools.getCurrentTimeStamp();

                    String genre;
                    if (me.getGender().equals("male")) {
                        genre = "homme";
                    } else {
                        genre = "femme";
                    }
                    SimpleUser localuser = new SimpleUser(me.getName(), me.getName(), genre, "1996-03-03", me.getName(),
                            me.getName(), me.getName() + me.getName(), "0", "0", "0",
                            "0", "0", localDate, "0", "0",
                            "0", "0", "0", "0");
                    new UserController().addUserFacebook(localuser);

                } catch (IOException ex) {

                }

                new MenuForm().show();

            }
        });
    }

    public static boolean firstLogin() {
        return Storage.getInstance().readObject("token") == null;
      //     return true;
    }

    public static void login(final Form form) {
        if (firstLogin()) {
            System.err.println("First login detected");
            LoginMain logForm = new LoginMain(form);
            logForm.show();
        } else {
            System.err.println("Welcome back");
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            User me = new User();
            UserController uc = new UserController();

            try {
                me = FaceBookAccess.getInstance().getUser("me");
                uc.getUser(me.getName(), me.getName() + me.getName());
            } catch (IOException ex) {
            }
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    //token has expired
                    if (code == 400) {
                        signIn(form);
                    }
                }
            });
            new MenuForm().show();
        }
    }
}
