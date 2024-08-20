package org.japzio.testing.testingdemo.v1.messaging;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/v1/messaging")
public class MessagingController {

    @GetMapping("/{message-id}")
    public Map<String, String> getMessageById(
        @RequestHeader("rrn")
        @Valid
        @UUID(message="rrn must be a valid UUID")
        String rrn,
        @PathVariable("message-id")
        @Valid
        @UUID(message="message-id must be a valid UUID")
        String messageId
    ) {
        return new HashMap<>(
                Map.of(
                        "rrn", rrn.toString(),
                        "id", messageId.toString(),
                        "content", "test content")
        );
    }

}
