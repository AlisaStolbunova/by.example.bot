package by.example.bot.service;

import by.example.bot.domain.City;
import by.example.bot.exception.CityNotFoundException;
import by.example.bot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public String getCityInfo(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName).map(City::getInfo).orElse("City not found");
    }

    public Optional<City> getCity(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName);
    }

    public City addCity(String name, String info) {
        return cityRepository.save(new City(name, info));
    }

    public City updateCity(Long id, String info) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        city.setInfo(info);
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    public Iterable<City> findAll(){
        return cityRepository.findAll();
    }
}
