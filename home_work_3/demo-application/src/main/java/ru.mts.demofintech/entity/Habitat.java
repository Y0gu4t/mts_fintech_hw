package ru.mts.demofintech.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"animals"})
@ToString(exclude = "animals")
@Table(schema = "animals")
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String area;

    @ManyToMany(mappedBy = "habitats", fetch = FetchType.EAGER)
    private Set<Animal> animals;
}
