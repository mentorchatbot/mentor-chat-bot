package com.chatbot.mentor.domain;

/**
 * @author junho.park
 */
public class BotMessage {
    private String botMessage;
    private boolean isExternalApi;
    private int jobCode;

    public BotMessage(String botMessage, boolean isExternalApi) {
        this.botMessage = botMessage;
        this.isExternalApi = isExternalApi;
    }

    public BotMessage(String botMessage, boolean isExternalApi, int jobCode) {
        this.botMessage = botMessage;
        this.isExternalApi = isExternalApi;
        this.jobCode = jobCode;
    }

    public String getBotMessage() {
        return botMessage;
    }

    public void setBotMessage(String botMessage) {
        this.botMessage = botMessage;
    }

    public boolean isExternalApi() {
        return isExternalApi;
    }

    public void setExternalApi(boolean externalApi) {
        isExternalApi = externalApi;
    }

    public int getJobCode() {
        return jobCode;
    }

    public void setJobCode(int jobCode) {
        this.jobCode = jobCode;
    }
}
