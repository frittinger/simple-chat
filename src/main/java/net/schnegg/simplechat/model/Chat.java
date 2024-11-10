package net.schnegg.simplechat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID customerId;
    private LocalDateTime startedAt;

    // this is set to the moment a customer finished the chat consensually, or after some time-out
    private LocalDateTime finishedAt;

    @Enumerated(EnumType.STRING)
    private ChatStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }
}
