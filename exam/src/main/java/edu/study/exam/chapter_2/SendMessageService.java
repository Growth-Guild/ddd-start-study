package edu.study.exam.chapter_2;

public class SendMessageService {
    private MessageSender messageSender;

    public SendMessageService(MessageSender awsMessageSender) {
        this.messageSender = awsMessageSender;
    }

    public SendMessageResult sendMessage(Message message) {
        return messageSender.send(message);
    }
}
