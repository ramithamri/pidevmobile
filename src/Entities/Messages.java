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
public class Messages {
    private int messageId;
    private int senderUserId;
    private int conversationId;
    private String messageContent;
    private String time;

    public Messages(int messageId, int senderUserId, int conversationId, String messageContent, String time) 
    {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.conversationId = conversationId;
        this.messageContent = messageContent;
        this.time = time;
    }

    public Messages() {
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getTime() {
        return time;
    }

   
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTime(String time) 
    {
        this.time = time;
    }
}
