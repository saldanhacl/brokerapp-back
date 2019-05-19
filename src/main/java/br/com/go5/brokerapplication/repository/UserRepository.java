package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}