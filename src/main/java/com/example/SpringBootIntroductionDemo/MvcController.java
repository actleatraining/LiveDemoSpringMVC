package com.example.SpringBootIntroductionDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MvcController {

    @Autowired
    DogRepository repository;

    @GetMapping("/dogmvc")
    String test() {
        return "view1";
    }

    @GetMapping("/dogmvc/{id}")
    String test2(Model model, @PathVariable Long id) {
        Dog dogFromDB = repository.findById(id).get();
        model.addAttribute( "dog", dogFromDB);
        return "view3";
    }

    @GetMapping("/doglist")
    String test3(Model model) {
        List<Dog> dogList = (List)repository.findAll();
        model.addAttribute( "dogs", dogList);
        return "view4";
    }

}
