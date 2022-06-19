package edu.study.exam.chapter_2;

public class Message {
    private String sender;
    private String receiver;
    private String title;
    private String content;

    public Message() {
    }

    public Message(String sender, String receiver, String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
    }
}
