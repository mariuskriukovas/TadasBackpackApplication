package com.tadas.backpackmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "item")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_mandatory")
    private Boolean isMandatory;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "provide_kilometers")
    private Double provideKilometers;
}