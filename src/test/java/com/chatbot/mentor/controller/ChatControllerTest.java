package com.chatbot.mentor.controller;

import com.chatbot.mentor.domain.ChatMessage;
import com.chatbot.mentor.dto.ChatMessageResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class ChatControllerTest {
    @Mock
    private ChatController chatController;

    private MockMvc mvc;
    private ChatMessage chatMessage;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(chatController).build();
        objectMapper = new ObjectMapper();
        chatMessage = new ChatMessage("안녕", "메세지 히스토리", "Jay");
    }

    @Test
    public void chatTest() throws Exception {
        ChatMessageResponseDto chatMessageResponseDto =
                new ChatMessageResponseDto("안녕", "그래 안녕", "");
        ResponseEntity<ChatMessageResponseDto> responseEntity = new ResponseEntity<>(chatMessageResponseDto, HttpStatus.OK);

        given(chatController.chatMessage(any(ChatMessage.class))).willReturn(responseEntity);

        mvc.perform(post("/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(chatMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userMessage", is("안녕")))
                .andExpect(jsonPath("$.chatBotMessage", is("그래 안녕")))
                .andExpect(jsonPath("$.messageHistory", is("")));
    }
}