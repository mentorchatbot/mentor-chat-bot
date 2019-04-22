package com.chatbot.mentor.dto;

/**
 * @author junho.park
 */
public class ChatMessageResponseDto {
    private String userMessage;
    private String chatBotMessage;
    private String messageHistory;

    public ChatMessageResponseDto() {
    }

    public ChatMessageResponseDto(String userMessage, String chatBotMessage, String messageHistory) {
        this.userMessage = userMessage;
        this.chatBotMessage = chatBotMessage;
        this.messageHistory = messageHistory;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getChatBotMessage() {
        return chatBotMessage;
    }

    public void setChatBotMessage(String chatBotMessage) {
        this.chatBotMessage = chatBotMessage;
    }

    public String getMessageHistory() {
        return messageHistory;
    }

    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }
}
