package com.chatbot.mentor.service;

import com.chatbot.mentor.dto.ChatMessageResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatServiceTest {
    @Autowired
    private ChatService socketService;

    private String userMessage = "안녕";
    private String botMessage = "그래 안녕";
    private String messageHistory = "";
    private String userName = "Jay";

    @Test
    public void shouldGetBotMessageTemplate() {
        String returnBotMessage = "\n[MENTOR]그래 안녕";
        ChatMessageResponseDto chatMessageResponseDto =
                socketService.getChatMessageResponse(userMessage, botMessage, messageHistory, userName);

        assertThat(chatMessageResponseDto.getChatBotMessage()).isEqualTo(returnBotMessage);
    }
}