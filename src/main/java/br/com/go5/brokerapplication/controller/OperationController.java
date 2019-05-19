package br.com.go5.brokerapplication.controller;

import br.com.go5.brokerapplication.model.request.Response;
import br.com.go5.brokerapplication.model.*;
import br.com.go5.brokerapplication.model.request.OrderRequest;
import br.com.go5.brokerapplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired UserRepository userRepository;
    @Autowired StockRepository stockRepository;
    @Autowired WalletRepository walletRepository;
    @Autowired StockWalletRepository stockWalletRepository;
    @Autowired OperationRepository operationRepository;
    @Autowired OperationOrderRepository operationOrderRepository;

    @RequestMapping(
            value="/buy",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Response> buyStock(@RequestBody OrderRequest request) {
        try {
            Boolean isSelling = false;
            OperationOrder sellingOrder = checkForSellingOrBuyingOrders(request, isSelling);
            return makeOperationOrOrder(request, sellingOrder, isSelling);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            String errorMessage = "Não foi possível completar operação"; // TODO criar constantes com mensagens de erro
            return new ResponseEntity<>(new Response(errorMessage, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(
            value="/sell",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Response> sellStock(@RequestBody OrderRequest request) {
        try {
            Boolean isSelling = true;
            OperationOrder buyingOrder = checkForSellingOrBuyingOrders(request, isSelling);
            return makeOperationOrOrder(request, buyingOrder, isSelling);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            String errorMessage = "Não foi possível completar operação"; // TODO criar constantes com mensagens de erro
            return new ResponseEntity<>(new Response(errorMessage, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Response> makeOperationOrOrder(OrderRequest request, OperationOrder foundOrder, Boolean isSelling) {
            OperationOrder newOrder = createOperationOrder(
                    new User(request.getUserId()),
                    new Stock(request.getStockId()),
                    request.getQuantity(),
                    request.getPrice(),
                    isSelling);
            if (foundOrder != null) {
                Operation operation = makeOperation(foundOrder, request);
                deleteOrDecreaseOrder(foundOrder, newOrder, request, isSelling);
                return new ResponseEntity<>(new Response(null, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response(null, newOrder), HttpStatus.OK);
            }
    }

    private void deleteOrDecreaseOrder(OperationOrder foundOrder, OperationOrder order, OrderRequest request, Boolean isSelling) {
        if (isSelling && order.getQuantity() > foundOrder.getQuantity()) {
            operationOrderRepository.decreaseStocksById(order.getId(), request.getQuantity());
            operationOrderRepository.delete(foundOrder);
        } else if (isSelling && order.getQuantity() < foundOrder.getQuantity()){
            operationOrderRepository.delete(order);
            operationOrderRepository.decreaseStocksById(foundOrder.getId(), request.getQuantity());
        } else if (!isSelling && order.getQuantity() > foundOrder.getQuantity()) {
            operationOrderRepository.decreaseStocksById(order.getId(), request.getQuantity());
            operationOrderRepository.delete(foundOrder);
        } else if (!isSelling && order.getQuantity() < foundOrder.getQuantity()) {
            operationOrderRepository.delete(order);
            operationOrderRepository.decreaseStocksById(foundOrder.getId(), request.getQuantity());
        } else if (order.getQuantity().equals(foundOrder.getQuantity())) {
            operationOrderRepository.delete(order);
            operationOrderRepository.delete(foundOrder);
        }
    }


    private OperationOrder createOperationOrder(User user, Stock stock, Integer quantity, Double price, Boolean isSell) {
        OperationOrder operationOrder = new OperationOrder(
                user,
                stock,
                quantity,
                price,
                isSell);
        return operationOrderRepository.save(operationOrder);
    }

    private Operation makeOperation(OperationOrder order, OrderRequest request) {
        try {
            Wallet wallet = walletRepository.findByUser(order.getUser()).get();
            StockWallet stockWallet = createStockWallet(
                    order.getStock(),
                    wallet,
                    order.getQuantity());
            Operation operation = createOperation(stockWallet);
            decreaseStocks(order.getStock().getId(), wallet, request.getQuantity());
            return operation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private StockWallet createStockWallet(Stock stock, Wallet wallet, Integer quantity) {
        StockWallet stockWallet = new StockWallet(stock, wallet, Long.valueOf(quantity));
        return stockWalletRepository.save(stockWallet);
    }

    private OperationOrder checkForSellingOrBuyingOrders(OrderRequest request, Boolean isSelling) {
        try {
            List<OperationOrder> orders;
            if (isSelling) {
                 orders = operationOrderRepository.findBuyingOrdersByStockWithPriceUnderAndQuantityGreater(
                        request.getStockId(),
                        request.getPrice(),
                        request.getQuantity()
                );
            } else {
                orders = operationOrderRepository.findSellingOrdersByStockWithPriceUnderAndQuantityGreater(
                        request.getStockId(),
                        request.getPrice(),
                        request.getQuantity()
                );
            }
            // TODO implementar um logica mais sofisticada (ex: por data)
            return !orders.isEmpty() ? orders.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Operation createOperation(StockWallet stockWallet) {
        return operationRepository.save(new Operation(stockWallet, new Date()));
    }

    private void decreaseStocks(Long stockId, Wallet wallet,Integer decreaseQuantity) {
        decreaseStockWallet(stockId, wallet, decreaseQuantity);
    }

    private void decreaseStockWallet(Long stockId, Wallet wallet, Integer decreaseQuantity) {
        createStockWallet(
                new Stock(stockId),
                wallet,
                decreaseQuantity*-1
        );
    }

}
