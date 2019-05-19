package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.User;
import br.com.go5.brokerapplication.model.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(User user);
}