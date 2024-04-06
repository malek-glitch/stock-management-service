package com.crocostaud.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumbers;

    /**the accule users of the shop with different roles (e.g. ADMIN, USER)*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<User> users = new LinkedHashSet<>();

/**the different warehouses where the shop stock its {@link Product products}
 * among them the main Local of the shop*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<Warehouse> warehouses = new LinkedHashSet<>();

    /** the {@link Client clients} of the {@link Shop}*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<Client> clients = new LinkedHashSet<>();

    /** this field represent the {@link Order orders} made by the shop to a {@link Provider}*/
    @OneToMany(mappedBy = "shop")
    @Builder.Default
    private Set<Order> orders = new LinkedHashSet<>();

    /** the list of {@link Provider providers} of the {@link Shop}*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<Provider> providers = new LinkedHashSet<>();

    /**the list of {@link Inventory inventories} of the {@link Shop}
     * where each {@link Inventory} represent the relation between a {@link Product} and the {@link Warehouse}
     *( <em> in short it represent where and how many of a certain {@link Product} are stored </em> )*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<Inventory> inventories = new LinkedHashSet<>();

    /**this field represent the list Of {@link Sell sells} made by the {@link Shop} to the {@link Client clients}*/
    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    @Builder.Default
    private Set<Sell> sells = new LinkedHashSet<>();

    public Shop(Long id){
        setId(id);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Shop shop = (Shop) o;
        return getId() != null && Objects.equals(getId(), shop.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}