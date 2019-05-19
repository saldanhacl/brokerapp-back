package br.com.go5.brokerapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stock_wallet")
    private StockWallet stockWallet;

    public Operation(StockWallet stockWallet, Date date) {
        this.stockWallet = stockWallet;
        this.date = date;
    }

}