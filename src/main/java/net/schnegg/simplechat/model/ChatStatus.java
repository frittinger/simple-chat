package net.schnegg.simplechat.model;

public enum ChatStatus {
    ACTIVE, // chat is still open and has an active conversation
    RESOLVED, // chat has been resolved and is no longer active
    QUIT // customer hung up or left the chat
}
