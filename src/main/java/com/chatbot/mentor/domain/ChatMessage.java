package com.chatbot.mentor.domain;

/**
 * @author junho.park
 */
public class ChatMessage {
    private String message;
    private String messageHistory;
    private String user;

    public ChatMessage() {
    }

    public ChatMessage(String message, String messageHistory, String user) {
        this.message = message;
        this.messageHistory = messageHistory;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageHistory() {
        return messageHistory;
    }

    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
