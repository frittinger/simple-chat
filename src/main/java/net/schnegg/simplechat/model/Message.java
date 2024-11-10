package net.schnegg.simplechat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;
    private LocalDateTime createdAt;
    private UUID supportAgentId;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

}

