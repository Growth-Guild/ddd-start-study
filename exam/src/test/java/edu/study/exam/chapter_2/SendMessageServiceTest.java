package edu.study.exam.chapter_2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendMessageServiceTest {

    @Test
    void sendTest() {
        // given
        Message message = new Message("CY", "JG", "Hello", "World");
        SendMessageResult givenMessageResult = new SendMessageResult(true, null);

        MessageSender messageSender = mock(MessageSender.class);
        when(messageSender.send(message)).thenReturn(givenMessageResult);

        SendMessageService sendMessageService = new SendMessageService(messageSender);

        // when
        SendMessageResult sendMessageResult = sendMessageService.sendMessage(message);

        // then
        assertThat(sendMessageResult.isSucceed()).isEqualTo(true);
        assertThat(sendMessageResult.getErrorMessage()).isNull();
    }
}
