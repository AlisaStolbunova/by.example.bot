package by.example.bot.repository;

import by.example.bot.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    Optional<City> findByNameIgnoreCase(String name);

    Optional<City> findById(Long id);
}
