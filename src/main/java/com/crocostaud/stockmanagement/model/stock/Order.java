package com.crocostaud.stockmanagement.model.stock;

import com.crocostaud.stockmanagement.model.utils.Commande;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;


/**
 * This class is used to represent an order made by the {@link Shop} to the  {@link Provider}
 * it stores the amount, date and the {@link com.crocostaud.stockmanagement.model.part.Part parts} and their Qts
 */
@SuperBuilder
@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order extends Commande {

    @ManyToOne
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<OrderItem> items;

    public Order() {
        super();
    }

    public Order(Long id) {
        super(id);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public final int hashCode() {

        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}




