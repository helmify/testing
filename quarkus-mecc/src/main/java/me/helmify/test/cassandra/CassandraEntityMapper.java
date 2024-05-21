package me.helmify.test.cassandra;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CassandraEntityMapper {

    @DaoFactory
    CassandraDao dao();

}
