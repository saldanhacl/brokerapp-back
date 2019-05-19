package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.StockWallet;
import br.com.go5.brokerapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockWalletRepository extends JpaRepository<StockWallet, Long> {
    @Query(value = "SELECT new br.com.go5.brokerapplication.model.StockWallet(sw.id, sum(sw.quantity)) "+
            "from StockWallet sw " +
            "where sw.wallet = ( " +
            "select id from Wallet w " +
            "    where w.user = ?1)" +
            "group by sw.stock")
    List<StockWallet> findByUser(User user);

}