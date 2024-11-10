package net.schnegg.simplechat.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.schnegg.simplechat.model.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID supportAgentId;
    private MessageType messageType;
}
