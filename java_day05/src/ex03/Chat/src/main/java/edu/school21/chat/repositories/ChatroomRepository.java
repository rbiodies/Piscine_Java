package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

public interface ChatroomRepository {
    Chatroom findById(Long id);
}
