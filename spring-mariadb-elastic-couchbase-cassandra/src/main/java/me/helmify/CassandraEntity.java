package me.helmify;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class CassandraEntity {

	@PrimaryKey
	private final String id;

	private final String name;

	public CassandraEntity(String id, String name ) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
