package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private long    id;
    private String  name;
    private User    owner;
    private List<Message> roomMessage;

    public Chatroom(long id, String name, User owner, List<Message> roomMessage) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.roomMessage = roomMessage;
    }

    public boolean  equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Chatroom    chatroom = (Chatroom) o;

        return id == chatroom.id && name.equals(chatroom.name) && owner.equals(chatroom.owner) && roomMessage.equals(chatroom.roomMessage);
    }

    public int  hashCode() {
        return Objects.hash(id, name, owner, roomMessage);
    }

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String   getName() {
        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String   toString() {
        return  "{id=" + id +
                ",name=\"" + name +
                "\",creator=" + owner +
                ",messages=" + roomMessage + "}";
    }
}
