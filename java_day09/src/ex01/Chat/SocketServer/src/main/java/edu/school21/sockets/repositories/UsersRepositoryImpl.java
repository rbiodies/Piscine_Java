package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM service.users WHERE id=?";

        return jdbcTemplate.query(sql, new Object[]{id}, new UserMapper()).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT * FROM service.users";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public void save(User entity) {
        final String sql = "INSERT INTO service.users (username, password) VALUES (?, ?)";

       jdbcTemplate.update(sql, entity.getUsername(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        final String sql = "UPDATE service.users SET username=?, password=? WHERE id=?";

        jdbcTemplate.update(sql, entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM service.users WHERE id=?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM service.users WHERE username=?";

        return jdbcTemplate.query(sql, new Object[]{username}, new UserMapper()).stream().findAny();
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }
    }
}
