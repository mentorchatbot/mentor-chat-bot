package com.chatbot.mentor.controller;

import com.chatbot.mentor.domain.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author junho.park
 */
@Controller
public class ChatController {
    @PostMapping("/control")
    @ResponseBody
    public String control(@RequestBody ChatMessage chat) {
        // TODO 서비스 정의 후 로직 작성
        return "message";
    }
}
