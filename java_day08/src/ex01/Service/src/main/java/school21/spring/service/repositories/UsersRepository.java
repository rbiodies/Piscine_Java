package school21.spring.service.repositories;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User>  findByEmail(String email);
}
