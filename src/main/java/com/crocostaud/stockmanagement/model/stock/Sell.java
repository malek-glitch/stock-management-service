package com.crocostaud.stockmanagement.model.stock;

import com.crocostaud.stockmanagement.model.utils.Commande;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter

@RequiredArgsConstructor
@Entity
public class Sell extends Commande {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    @OneToMany(mappedBy = "sell", orphanRemoval = true)
    private Set<SellItem> sellItems = new LinkedHashSet<>();

}



