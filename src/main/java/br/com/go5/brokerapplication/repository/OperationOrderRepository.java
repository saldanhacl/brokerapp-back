package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.OperationOrder;
import br.com.go5.brokerapplication.model.Stock;
import br.com.go5.brokerapplication.model.User;
import br.com.go5.brokerapplication.model.projection.SellOrderProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OperationOrderRepository extends CrudRepository<OperationOrder, Long> {

    List<OperationOrder> findByUserAndIsSell(User user, Boolean isSell);

    @Query(value = "SELECT * "+
            "from operation_order op " +
            "where op.stock = ?1 " +
            "and op.price <= ?2 " +
            "and op.quantity >= ?3 "+
            "and op.is_sell = 1 ",
            nativeQuery = true)
    List<OperationOrder> findSellingOrdersByStockWithPriceUnderAndQuantityGreater(
            Long stockId,
            Double price,
            Integer quantity
    );

    @Query(value = "SELECT * "+
            "from operation_order op " +
            "where op.stock = ?1 " +
            "and op.price >= ?2 " +
            "and op.is_sell = 0 ",
            nativeQuery = true)
    List<OperationOrder> findBuyingOrdersByStockWithPriceUnderAndQuantityGreater(
            Long stockId,
            Double price,
            Integer quantity
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE OperationOrder op SET op.quantity = op.quantity - ?2 WHERE op.id = ?1")
    void decreaseStocksById(Long id, Integer decreaseQuantity);

}