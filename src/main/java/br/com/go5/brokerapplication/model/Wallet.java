package br.com.go5.brokerapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="user")
    private User user;

    @OneToMany(mappedBy="wallet", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    List<StockWallet> stocks;

    public Wallet(Long id) {
        this.id = id;
    }
}