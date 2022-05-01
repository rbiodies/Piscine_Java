package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private long        id;
    private User        author;
    private Chatroom    chatroom;
    private String      text;
    private LocalDateTime   messageDataTime;

    public Message(long id, User author, Chatroom chatroom, String text, LocalDateTime messageDataTime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.messageDataTime = messageDataTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public String   getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean  equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message    message = (Message) o;

        return id == message.id && author.equals(message.author) && chatroom.equals(message.chatroom) && text.equals(message.text) && messageDataTime.equals(message.messageDataTime);
    }

    public int  hashCode() {
        return Objects.hash(id, author, chatroom, text, messageDataTime);
    }

    public String   toString() {
        return "User{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text=" + text +
                ", messageDataTime=" + messageDataTime + "}";
    }
}
