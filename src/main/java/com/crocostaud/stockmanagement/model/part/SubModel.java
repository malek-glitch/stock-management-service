package com.crocostaud.stockmanagement.model.part;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "submodel")
public class SubModel {

    @Id
    private Long id;
    @Column(name = "energy_type")
    private String energyType;
    private int power;
    private String description;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;


//    @ManyToMany(mappedBy = "compatibleVehicle")
//    private Set<Original> originalParts;


    @Override
    public String toString() {
        return "SubModel{" +
                "id=" + id +
                ", energyType='" + energyType + '\'' +
                ", power=" + power +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SubModel subModel = (SubModel) o;
        return getId() != null && Objects.equals(getId(), subModel.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
