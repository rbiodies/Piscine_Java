package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("driverManagerBean") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UsersRepositoryJdbcTemplateImpl() {
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM roomdb.users WHERE id = ?",
                new Object[]{id}, new UserMapper()).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM roomdb.users", new UserMapper());
    }

    @Override
    public void save(User entity) {
//        jdbcTemplate.update("INSERT INTO roomdb.users (email) VALUES (?)",
//                entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE roomdb.users SET email = ? WHERE id = ?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM roomdb.users WHERE id = ?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM roomdb.users WHERE email = ?",
                new Object[]{email}, new UserMapper()).stream().findAny();
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(UUID.fromString(rs.getString("password")));

            return user;
        }
    }
}
