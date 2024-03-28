package com.example.springcassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CassandraRepo extends CassandraRepository<Entity, String> {
}
