package ru.mts.demofintech.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"id", "animals"})
@Table(schema = "animals")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String breed;

    @OneToMany(mappedBy = "breed", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
