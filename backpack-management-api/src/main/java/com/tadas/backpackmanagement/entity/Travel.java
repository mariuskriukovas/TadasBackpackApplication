package com.tadas.backpackmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "travel")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "distance_kilometers")
    private Double distanceKilometers;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id", nullable = false)
    Traveler traveler;
}