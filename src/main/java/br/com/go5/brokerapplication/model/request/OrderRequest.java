package br.com.go5.brokerapplication.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderRequest {

    // TODO fazer JSON Properties funcionar pra colocar cammelCase

    @NotNull
    private Long userId;

    @NotNull
    private Long stockId;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

}