package com.crocostaud.stockmanagement.model.utils;

import com.crocostaud.stockmanagement.model.stock.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected LocalDateTime date;

    /** the total price of the {@link Commande} <br>
     * this field is calculated as the sum of all prices of the commandItems*/
    protected Double totalPrice;

    /**this field represent the discount percentage for the item (e.g. 15%, 10%)*/
    @Builder.Default()
    protected int discount = 0;

    /**this field represent the paid amount of the total price of the {@link Commande commande} <br/>
     * if the paid amount is equivalent to the total price then the {@link Order order}
     * is considered paid <br/> otherwise the {@link Order order} is not fully paid and there is a {@code REST}<br>
     * <em>the {@code REST} is calculated as: </em> <br>
     *<pre><code> REST = (totalPrice - (totalPrice * discount) / 100 ) -  paidAmount </code></pre>
     **/
    protected Double paidAmount ;

    public Commande(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Commande commande = (Commande) o;
        return getId() != null && Objects.equals(getId(), commande.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
