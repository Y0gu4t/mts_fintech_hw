package ru.mts.demofintech.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "animals"})
@Builder
@Table(name = "animal_type", schema = "animals")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private Boolean isWild;

    @OneToMany(mappedBy = "animalType", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
