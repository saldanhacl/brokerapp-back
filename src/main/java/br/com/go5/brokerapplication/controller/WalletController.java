package br.com.go5.brokerapplication.controller;

import br.com.go5.brokerapplication.model.*;
import br.com.go5.brokerapplication.model.request.FindStocksByUserRequest;
import br.com.go5.brokerapplication.model.request.Response;
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

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired UserRepository userRepository;
    @Autowired StockWalletRepository stockWalletRepository;

    @RequestMapping(
            value="/stocks",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Response> findStocksByUser(@RequestBody FindStocksByUserRequest request) {
        try {
            User user = userRepository.findById(request.getUserId()).get();
//            List<StockWalletSum> stocks = stockWalletRepository.getGetTotalQuantity(user);
//            return new ResponseEntity<>(new Response(null, stocks), HttpStatus.OK);
            return null;
        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
            String errorMessage = "Não foi possível completar operação"; // TODO criar constantes com mensagens de erro
            return new ResponseEntity<>(new Response(errorMessage, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
