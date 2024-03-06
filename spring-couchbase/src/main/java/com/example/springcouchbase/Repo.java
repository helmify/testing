package com.example.springcouchbase;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;


interface Repo extends CouchbaseRepository<Fruit, String> {
}
