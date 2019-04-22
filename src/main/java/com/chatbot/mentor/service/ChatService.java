package com.chatbot.mentor.service;

import com.chatbot.mentor.dto.ChatMessageResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author junho.park
 */
@Service
public class ChatService {
    private static final String BOT_NAME = "MENTOR";
    private static final String ALIGN = "\n";

    public ChatMessageResponseDto getChatMessageResponse(String userMessage, String botMessage, String messageHistory, String userName) {
        return new ChatMessageResponseDto(convertToTemplateWithNameAndMessage(userName, userMessage),
                convertToTemplateWithNameAndMessage(BOT_NAME, botMessage), messageHistory);
    }

    private String convertToTemplateWithNameAndMessage(String name, String message) {
        return ALIGN + "[" + name + "]" + message;
    }
}
