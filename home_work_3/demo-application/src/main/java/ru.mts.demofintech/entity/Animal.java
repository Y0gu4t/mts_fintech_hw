package ru.mts.demofintech.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "habitats", "providers"})
@Builder
@Table(schema = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String character;

    private BigDecimal cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "animal_type_id")
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_habitats",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "habitat_id")
    )
    private Set<Habitat> habitats = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_providers",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private Set<Provider> providers = new HashSet<>();

    @JsonValue
    public String toJsonString() {
        return "{type:" + getAnimalType() +
                ", breed:" + breed +
                ", name:" + name +
                ", cost:" + cost +
                ", character:" + character +
                ", birthdate:" + birthday + '}';
    }
}
