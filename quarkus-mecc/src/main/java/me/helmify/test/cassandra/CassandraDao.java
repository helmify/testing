package me.helmify.test.cassandra;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface CassandraDao {

    @Insert
    void save(CassandraEntity entity);

    @Select
    PagingIterable<CassandraEntity> findAll();
}
