package com.chatbot.mentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author junho.park
 */
@Controller
public class MentorController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
