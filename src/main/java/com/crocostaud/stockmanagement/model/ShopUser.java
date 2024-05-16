package com.crocostaud.stockmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "user")
@AllArgsConstructor
@ToString
public class ShopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
//
//    @Override
//    public String toString() {
//        return "ShopUser{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + "[PROTECTED]" + '\'' +
//                ", email='" + email + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }

    private String email;

    private String role;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ShopUser shopUser = (ShopUser) o;
        return getId() != null && Objects.equals(getId(), shopUser.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
