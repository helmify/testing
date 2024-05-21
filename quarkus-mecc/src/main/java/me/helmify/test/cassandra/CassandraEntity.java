package me.helmify.test.cassandra;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

@Entity
@PropertyStrategy(mutable = false)
public class CassandraEntity {

    @PartitionKey
    private final String name;

    public CassandraEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
