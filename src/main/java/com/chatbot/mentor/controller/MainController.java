package com.chatbot.mentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author junho.park
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String initMain() {
        return "index";
    }
}
