package com.javanei.emulation.emuldb.game;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vanei
 */
public class GameImporterMessage {

    private final Type type;
    private final String message;
    private final List<GameImporterMessage> messages = new LinkedList<>();

    public GameImporterMessage(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void addMessage(GameImporterMessage msg) {
        this.messages.add(msg);
    }

    public void addMessages(List<GameImporterMessage> msgs) {
        this.messages.addAll(msgs);
    }

    public List<GameImporterMessage> getMessages() {
        return messages;
    }

    public boolean isError() {
        if (this.type.equals(Type.ERROR)) {
            return true;
        }
        return this.messages.stream().anyMatch((msg) -> (msg.isError()));
    }

    public boolean isWarn() {
        if (!this.isError()) {
            if (this.type.equals(Type.WARN)) {
                return true;
            }
        }
        return this.messages.stream().anyMatch((msg) -> (msg.isWarn()));
    }

    public boolean isInfo() {
        return !this.isError() && !this.isWarn();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(": ").append(message);
        if (!this.messages.isEmpty()) {
            this.messages.stream().forEach((msg) -> {
                sb.append("\n\t").append(msg.toString());
            });
        }
        return sb.toString();
    }

    public enum Type {

        INFO,
        WARN,
        ERROR
    }
}
