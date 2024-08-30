package org.japzio.testing.testingdemo.v1.messaging.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.japzio.testing.testingdemo.v1.messaging.connector.MessageApiConnector;
import org.japzio.testing.testingdemo.v1.messaging.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/v1/messaging")
public class MessagingController {

    @Autowired
    private MessageApiConnector messageApiConnector;

    @GetMapping("/{message-id}")
    public Message getMessageById(
        @RequestHeader("rrn")
        @Valid
        @UUID(message="rrn must be a valid UUID")
        String rrn,
        @PathVariable("message-id")
        @Valid
        @UUID(message="message-id must be a valid UUID")
        String messageId
    ) {
        return messageApiConnector
                .getMessage(java.util.UUID.randomUUID().toString());
    }

}
