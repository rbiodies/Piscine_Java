package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    public JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(JDBCGenerator dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource.managerDataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM roomdb.users WHERE id = ?", new Object[]{id}, (rs, ))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from roomdb.users", (rs, rowNum) -> new User(rs.getLong("")));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("insert into roomdb.users (email) values (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return null;
    }
}
