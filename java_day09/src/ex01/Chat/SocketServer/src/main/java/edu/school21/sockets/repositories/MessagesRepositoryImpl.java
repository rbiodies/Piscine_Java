package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MessagesRepositoryImpl implements CrudRepository<Message> {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message entity) {
        final String sql = "INSERT INTO service.messages (sender, text, time) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, entity.getSender().getId(), entity.getText(), entity.getTime());
    }

    @Override
    public void update(Message entity) {
        final String sql = "UPDATE service.messages SET sender=?, text=?, time=? WHERE id=?";

        jdbcTemplate.update(sql, entity.getSender().getId(), entity.getText(), entity.getTime(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM service.messages WHERE id=?";

        jdbcTemplate.update(sql, id);
    }
}
