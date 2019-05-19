package br.com.go5.brokerapplication.model.projection;

import br.com.go5.brokerapplication.model.OperationOrder;
import br.com.go5.brokerapplication.model.Stock;
import br.com.go5.brokerapplication.model.StockWallet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "stockWalletSum", types = {StockWallet.class})
public interface StockWalletSumProjection {

    Integer getQuantity();

}
