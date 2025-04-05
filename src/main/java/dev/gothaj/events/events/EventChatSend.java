package dev.gothaj.events.events;

import dev.gothaj.events.Event;

public class EventChatSend extends Event {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventChatSend(String message) {
        this.message = message;
    }
}
