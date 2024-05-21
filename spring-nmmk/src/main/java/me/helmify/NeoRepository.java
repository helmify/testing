package me.helmify;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoRepository extends Neo4jRepository<NeoEntity, Long> {
}
