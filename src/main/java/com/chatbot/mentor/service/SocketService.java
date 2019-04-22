package com.chatbot.mentor.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author junho.park
 */
@Service
public class SocketService {
    private static final char NULL_CHAR = (char) 0;
    private static final int DEFAULT_CHAT_SCRIPT_PORT = 1024;
    private static final String DEFAULT_HOST = "localhost";

    public String getBotMessage(String message, String userName) {
        verifyChatArguments(message, userName);

        // TODO 메세지 보내는 부분 개선필요할 것 같음 issue: https://github.com/mentorchatbot/mentor-chat-bot/issues/11
        return getBotMessageUsingChatScriptProtocol(userName + NULL_CHAR + NULL_CHAR + message + NULL_CHAR);
    }

    // TODO Socket과 통신 관련 객체들이 Thread-Safe 한가?, 일단 인스턴스 변수로 올리지 않았음. 내부로직 개선 필요
    private String getBotMessageUsingChatScriptProtocol(String mess) {
        Socket echoSocket;
        String resp = "";

        try {
            echoSocket = new Socket(DEFAULT_HOST, DEFAULT_CHAT_SCRIPT_PORT);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            out.println(mess);
            resp = in.readLine();
            echoSocket.close();
        } catch (IOException e) {
            // TODO 로깅을 어떻게 할 것인가
            System.out.println("Error: " + e.getMessage());
        }
        return resp;
    }

    private void verifyChatArguments(String message, String userName) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("메세지를 입력하세요");
        }
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("유저네임을 입력하세요");
        }
    }
}
