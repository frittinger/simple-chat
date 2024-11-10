package net.schnegg.simplechat.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.schnegg.simplechat.model.Chat;
import net.schnegg.simplechat.model.Message;
import net.schnegg.simplechat.model.MessageType;
import net.schnegg.simplechat.service.ChatNotFoundException;
import net.schnegg.simplechat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDTO> getChat(@PathVariable UUID chatId) {
        log.debug("### Request chat with ID: {}", chatId);
        Chat chat = chatService.loadChat(chatId);
        if (null == chat) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(toChatDTO(chat));
    }

    @PostMapping("/new-chat")
    public ResponseEntity<ChatDTO> createNewChat(@RequestBody MessageDTO message) {
        log.debug("### Create new chat with message: {}", message.toString());

        Chat chat = chatService.createNewChat(toMessage(message));

        return ResponseEntity.ok().body(toChatDTO(chat));
    }

    @PostMapping("/customer/{chatId}")
    public ResponseEntity createCustomerMessage(@PathVariable UUID chatId, @RequestBody MessageDTO message) {
        log.debug("### Create customer message for chat {}: {}", chatId, message.toString());

        Message messageEntity = toMessage(message);

        try {
            chatService.addCustomerMessage(chatId, messageEntity);
        } catch (ChatNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping("/support/{chatId}")
    public ResponseEntity createSupportMessage(@PathVariable UUID chatId, @RequestBody MessageDTO message) {
        log.debug("### Create support message for chat {}: {}", chatId, message.toString());
        try {
            chatService.addsupportMessage(chatId, toMessage(message));
        } catch (ChatNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // The following code would be externalized to MapStruct or similar ...

    private ChatDTO toChatDTO(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setCustomerId(chat.getCustomerId());
        chatDTO.setStartedAt(chat.getStartedAt());
        chatDTO.setFinishedAt(chat.getFinishedAt());
        chatDTO.setStatus(chat.getStatus());
        chatDTO.setMessages(chat.getMessages());
        return chatDTO;
    }

    private Message toMessage(MessageDTO message) {
        Message messageEntity = new Message();
        messageEntity.setContent(message.getContent());
        return messageEntity;
    }
}
