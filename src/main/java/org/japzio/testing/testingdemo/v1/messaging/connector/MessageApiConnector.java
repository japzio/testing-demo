package org.japzio.testing.testingdemo.v1.messaging.connector;

import lombok.extern.slf4j.Slf4j;
import org.japzio.testing.testingdemo.v1.messaging.model.Message;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class MessageApiConnector {

    public Message getMessage(String messageId) {
        RestClient client = RestClient.create();
        return client
                .get()
                .uri("http://localhost:7000/v1/message/" + messageId)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Non HttpOk status: {}", response.getStatusCode().toString());
                    throw new RuntimeException();
                })
                .body(Message.class);
    }

}
