package br.com.go5.brokerapplication.repository;

import br.com.go5.brokerapplication.model.Operation;
import br.com.go5.brokerapplication.model.Stock;
import br.com.go5.brokerapplication.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Long> {
}