package ru.mts.demofintech.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "id_habitat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String area;

    @ManyToMany(mappedBy = "habitats", fetch = FetchType.EAGER)
    private Set<Animal> animals;
}
