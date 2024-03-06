package com.example.SpringBootIntroductionDemo;

import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
