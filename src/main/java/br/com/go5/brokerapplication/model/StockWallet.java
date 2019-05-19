package br.com.go5.brokerapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class StockWallet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="stock")
    private Stock stock;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="wallet")
    private Wallet wallet;

    @NotNull
    private Long quantity;

    public StockWallet(Stock stock, Wallet wallet, Long quantity) {
        this.stock = stock;
        this.wallet = wallet;
        this.quantity = quantity;
    }

    public StockWallet(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}