package ru.mts.demofintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
