package br.com.go5.brokerapplication.model.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class BuyStockRequest {

    // TODO fazer JSON Properties funcionar pra colocar cammelCase

    @NotNull
    private Long walletId;

    @NotNull
    private Long stockId;

    @NotNull
    private Integer quantity;

}