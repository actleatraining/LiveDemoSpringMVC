package com.example.SpringBootIntroductionDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    DogRepository repository;

    public DogController(DogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dog")
    public List<Dog> dogs() {
        return (List)repository.findAll();
    }

    @GetMapping("/dog/{id}")
    public Dog dog(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/dog")
    public Dog post(@RequestBody Dog dog) {
        return repository.save(dog);
    }

    @PutMapping("/dog/{id}")
    public Dog put(@PathVariable Long id, @RequestBody Dog dog) {
        if (dog.getId() == null) {
            dog.setId(id);
        }
        return repository.save(dog);
    }

    @DeleteMapping("/dog/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
