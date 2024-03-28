package com.example.springcassandra;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OnStartup {

    private final CassandraRepo repo;

    public OnStartup(CassandraRepo repo) {
        this.repo = repo;
    }

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        System.out.println("Application started");
        Entity entity = new Entity();
        entity.setName("test");
        entity.setId(UUID.randomUUID().toString());

        repo.save(entity);

        Entity next = repo.findAll().iterator().next();
        System.out.println("Entity: " + next.getName());

    }

}
