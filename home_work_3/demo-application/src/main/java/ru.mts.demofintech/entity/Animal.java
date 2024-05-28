package ru.mts.demofintech.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@Table(schema = "animals", name = "animal")
public class Animal {
    @Id
    @Column(name = "id_animal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String character;

    private BigDecimal cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_habitats",
            joinColumns = @JoinColumn(name = "id_animal"),
            inverseJoinColumns = @JoinColumn(name = "id_habitat")
    )
    private Set<Habitat> habitats;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_providers",
            joinColumns = @JoinColumn(name = "id_animal"),
            inverseJoinColumns = @JoinColumn(name = "id_provider")
    )
    private Set<Provider> providers;

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
