package com.crocostaud.stockmanagement.model.part;


import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.model.stock.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "part")
public class Part {
    @Id
    private Long id;
    private String ref;
    private String name;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @OneToMany(mappedBy = "part")
    private Set<Description> descriptions;

    @ManyToMany
    @JoinTable(name = "Part_original",
            joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orignal_part_oem", referencedColumnName = "oem"))
    private Set<OriginalPart> oems;

    @ManyToOne
    @JoinColumn(name = "suppliers_name")
    private Supplier supplier;

    @OneToMany(mappedBy = "part", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Inventory> inventories = new LinkedHashSet<>();

    public Part() {
        oems = new HashSet<>();
        descriptions = new HashSet<>();
    }

    public Part(Long id) {
        this.id = id;
    }

    public PartDto toDTO() {
        return PartDto.builder()
                .id(id)
                .ref(ref)
                .name(name)
                .imageUrl(imageUrl)
                .build();
    }
}

