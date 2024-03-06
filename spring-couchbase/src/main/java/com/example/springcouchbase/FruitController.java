package com.example.springcouchbase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class FruitController {

        private final Repo repo;

        public FruitController(Repo repo) {
            this.repo = repo;
        }

        @GetMapping("/fruits")
        public List<?> getFruits() {

            repo.deleteAll();

            Fruit fruit = new Fruit();

            fruit.setName("Potato");
            fruit.setDescription("Potato is a vegetable");
            repo.save(fruit);

            return repo.findAll();
        }

    }
