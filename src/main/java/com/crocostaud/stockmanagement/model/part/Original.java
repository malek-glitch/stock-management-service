package com.crocostaud.stockmanagement.model.part;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "original")
public class Original {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * OEM (Original Equipment Manufacturer) is the reference of the original spare part
     */
    private String oem;
    private String designation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "original_submodel",
            joinColumns = @JoinColumn(name = "original_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "compatible_submodel", referencedColumnName = "id"))
    private Set<SubModel> compatibleSubmodels = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "original_model",
            joinColumns = @JoinColumn(name = "original_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "compatible_model", referencedColumnName = "id"))
    private Set<Model> compatibleModels = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "original_part",
            joinColumns = @JoinColumn(name = "original_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
    private Set<Part> parts; // compatibles parts
}
