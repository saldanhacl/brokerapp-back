package br.com.go5.brokerapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotNull
    private User user;

    @NotNull
    private Stock stock;

}