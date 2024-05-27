package ru.mts.demofintech.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AnimalDTO {
    private String name;
    private String character;
    private BigDecimal cost;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Integer animalTypeId;
    private Integer breedId;
}
