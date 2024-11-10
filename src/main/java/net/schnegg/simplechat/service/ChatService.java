package net.schnegg.simplechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.schnegg.simplechat.model.Chat;
import net.schnegg.simplechat.model.ChatStatus;
import net.schnegg.simplechat.model.Message;
import net.schnegg.simplechat.model.MessageType;
import net.schnegg.simplechat.persistence.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat loadChat(UUID chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    public void addCustomerMessage(UUID chatId, Message message) throws ChatNotFoundException {
        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        message.setCreatedAt(LocalDateTime.now());
        message.setMessageType(MessageType.CUSTOMER);
        chat.addMessage(message);
    }

    public void addsupportMessage(UUID chatId, Message message) throws ChatNotFoundException {
        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        message.setCreatedAt(LocalDateTime.now());
        message.setMessageType(MessageType.SUPPORT);
        // a support chat could be handed over to another better qualified support agent (or from bot to human)
        message.setSupportAgentId(getSupportAgent());
        chat.addMessage(message);
    }

    private UUID getSupportAgent() {
        return UUID.randomUUID(); // Replace with actual logic to get support agent ID
    }

    public Chat createNewChat(Message message) {
        Chat newChat = new Chat();
        newChat.setStartedAt(LocalDateTime.now());
        message.setCreatedAt(LocalDateTime.now());
        // a new chat is always initiated by customers
        message.setMessageType(MessageType.CUSTOMER);
        newChat.addMessage(message);
        newChat.setCustomerId(getCustomerId());
        newChat.setStatus(ChatStatus.ACTIVE);
        return chatRepository.save(newChat);
    }

    // Get customer ID from context or from asking user, etc.
    private UUID getCustomerId() {
        return UUID.randomUUID(); // Replace with actual logic to get customer ID
    }
}
