package br.com.go5.brokerapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @OneToOne
    @JoinColumn(name = "wallet")
    private Wallet wallet;

    public User(Long id) {
        this.id = id;
    }
}