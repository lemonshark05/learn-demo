package com.example.learndemo.Model;

public class Chat {
    private int userId;
    private String answer;
    private int chatType;

    public Chat(int userId, String question, int chatType) {
        this.userId = userId;
        this.answer = question;
        this.chatType = 0;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }
}