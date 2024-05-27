package ru.mts.demofintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.demofintech.entity.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
}
