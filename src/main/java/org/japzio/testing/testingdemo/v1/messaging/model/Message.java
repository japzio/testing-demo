package org.japzio.testing.testingdemo.v1.messaging.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Message {
    public String rayId;
    public String message;
    public Instant sent;
    public Instant lastRead;
}
