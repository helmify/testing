package me.helmify;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("RedisEntity")
public class RedisEntity {

    private String id;
    private String value;

    public RedisEntity() {
    }

    public RedisEntity(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RedisEntity{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
