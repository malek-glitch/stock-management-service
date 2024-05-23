package com.crocostaud.stockmanagement.model.part;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Model {
    @Id
    private Long id;
    private String ref;
    private String label;
    private String name;
    @Column(name = "started_at")
    private Date startedAt;
    @Column(name = "ended_in")
    private Date endedIn;
    private String description;
    @ManyToOne
    @JoinColumn(name = "maker_id")
    private Maker maker;

    @Override
    public String toString() {
        return label + "{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", startedAt=" + startedAt +
                ", endedIn=" + endedIn +
                ", maker=" + maker +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Model model = (Model) o;
        return getId() != null && Objects.equals(getId(), model.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
