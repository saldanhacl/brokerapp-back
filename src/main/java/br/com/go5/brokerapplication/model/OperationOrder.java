package br.com.go5.brokerapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationOrder {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user")
    private User user;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stock")
    private Stock stock;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Boolean isSell;

    public OperationOrder(@NotNull User user, @NotNull Stock stock, @NotNull Integer quantity, @NotNull Double price, @NotNull Boolean isSell) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.isSell = isSell;
    }
}