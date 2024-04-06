package com.crocostaud.stockmanagement.model;

import com.crocostaud.stockmanagement.model.utils.Commande;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Sell extends Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    @OneToMany(mappedBy = "sell", orphanRemoval = true)
    private Set<SellItem> sellItems = new LinkedHashSet<>();

}



