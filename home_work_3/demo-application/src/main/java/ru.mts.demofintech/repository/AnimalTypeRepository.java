package ru.mts.demofintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.demofintech.entity.AnimalType;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
}
