package net.schnegg.simplechat.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.schnegg.simplechat.model.ChatStatus;
import net.schnegg.simplechat.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatDTO {
    private UUID id;
    private UUID customerId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private ChatStatus status;
    private List<Message> messages = new ArrayList<>();
}
