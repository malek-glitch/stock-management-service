package com.crocostaud.stockmanagement.model.part;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maker")
public class Maker {
    @Id
    private Long id;
    private String name;
    private String image;

    @Override
    public String toString() {
        return "Maker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
