package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FruitService {

  @Inject
  FruitDao dao;

  public void save(Fruit fruit) {
    dao.update(fruit);
  }

  public List<Fruit> getAll() {
    return dao.findAll().all();
  }
}
