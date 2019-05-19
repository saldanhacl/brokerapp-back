package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface StockRepository extends CrudRepository<Stock, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Stock s SET s.quantity = s.quantity - ?2 WHERE s.id = ?1")
    void decreaseStocksById(Long id, Integer decreaseQuantity);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Stock s SET s.unitPrice = s.unitPrice + ?1")
    void updateStocksPrices(Double value);
}