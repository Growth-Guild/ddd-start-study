package edu.study.exam.chapter_2;

public class SendMessageResult {
    private boolean isSucceed;
    private String errorMessage;

    public SendMessageResult(boolean isSucceed, String errorMessage) {
        this.isSucceed = isSucceed;
        this.errorMessage = errorMessage;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
