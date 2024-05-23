package com.crocostaud.stockmanagement.model.part;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "originalpart")
public class OriginalPart {

    // OEM (Original Equipment Manufacturer) is the reference of the original spare part
    @Id
    private String oem;
    private String designation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehicle_originalparts",
            joinColumns = @JoinColumn(name = "original_part_oem", referencedColumnName = "oem"),
            inverseJoinColumns = @JoinColumn(name = "compatible_vehicle", referencedColumnName = "id"))
    private Set<SubModel> compatibleVehicle;

    @ManyToMany(mappedBy = "oems")
    private Set<Part> compatibleParts;

}
