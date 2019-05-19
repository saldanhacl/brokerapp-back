package br.com.go5.brokerapplication.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FindStocksByUserRequest {

    // TODO fazer JSON Properties funcionar pra colocar cammelCase

    @NotNull
    private Long userId;

}