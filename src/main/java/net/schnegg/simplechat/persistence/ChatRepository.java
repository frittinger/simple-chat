package net.schnegg.simplechat.persistence;

import net.schnegg.simplechat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}