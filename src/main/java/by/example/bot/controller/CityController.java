package by.example.bot.controller;

import by.example.bot.domain.City;
import by.example.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    City create(@RequestParam String name, @RequestParam String info) {
        return cityService.addCity(name, info);
    }

    @PutMapping("/{id}")
    void update(@PathVariable(value = "id") Long id, @RequestParam String info){
        cityService.updateCity(id, info);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable(value = "id") Long id){
        cityService.deleteCity(id);
    }

    @GetMapping("/{id}")
    public City findCity(@PathVariable(value = "id") Long id){
        return cityService.findById(id);
    }

    @GetMapping("/all")
    public Iterable<City> findAll(){
        return cityService.findAll();
    }
}
