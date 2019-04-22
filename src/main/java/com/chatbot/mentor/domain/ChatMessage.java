package com.chatbot.mentor.domain;

/**
 * @author junho.park
 */
public class ChatMessage {
    private String userMessage;
    private String messageHistory;
    private String user;

    public ChatMessage() {
    }

    public ChatMessage(String userMessage, String messageHistory, String user) {
        this.userMessage = userMessage;
        this.messageHistory = messageHistory;
        this.user = user;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
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
