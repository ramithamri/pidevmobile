/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Lowkeyz
 */
public class Conversation {
    private int conversationId;
    private int userOneId;
    private int userTwoId;
    private String date;
    private int status;
    

    public Conversation() {
    }

    public Conversation(int conversationId, int userOneId, int userTwoId, String date,int status) {
        this.conversationId = conversationId;
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.date = date;
        this.status = status;
        
    }
    public void setId(int status) {
        this.conversationId = status;
    }
    public int getId() {
        return conversationId;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConversationId() {
        return conversationId;
    }

    public int getUserOneId() {
        return userOneId;
    }

    public int getUserTwoId() {
        return userTwoId;
    }

   

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public void setUserOneId(int userOneId) {
        this.userOneId = userOneId;
    }

    public void setUserTwoId(int userTwoId) {
        this.userTwoId = userTwoId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    
}
