package com.chatbot.mentor.controller;

import com.chatbot.mentor.domain.BotMessage;
import com.chatbot.mentor.domain.ChatMessage;
import com.chatbot.mentor.dto.ChatMessageResponseDto;
import com.chatbot.mentor.service.ChatService;
import com.chatbot.mentor.service.SocketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junho.park
 */
@RestController
public class ChatController {
    private final ChatService chatService;
    private final SocketService socketService;

    public ChatController(ChatService chatService, SocketService socketService) {
        this.chatService = chatService;
        this.socketService = socketService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatMessageResponseDto> chatMessage(@RequestBody ChatMessage chat) {
        String userMessage = chat.getUserMessage();
        String messageHistory = chat.getMessageHistory();
        String userName = chat.getUser();

        BotMessage botMessage = socketService.getBotMessage(userMessage, userName);
        ChatMessageResponseDto chatMessageResponseDto = chatService.getChatMessageResponse(userMessage, botMessage.getBotMessage(), messageHistory, userName);

        if (botMessage.isExternalApi()) {
            chatMessageResponseDto.setJobCode(botMessage.getJobCode());
        }

        return new ResponseEntity<>(chatMessageResponseDto, HttpStatus.OK);
    }
}
