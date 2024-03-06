package com.example.springcouchbase;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;

@Component
public class OnStartup {
    @Value("${spring.data.couchbase.bucket-name}")
    private String bucketName;
    @Autowired
    Cluster cluster;

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        System.out.println("Application started");
        CreatePrimaryQueryIndexOptions opts = CreatePrimaryQueryIndexOptions
                .createPrimaryQueryIndexOptions()
                .ignoreIfExists(true);

        cluster.queryIndexes().createPrimaryIndex(bucketName, opts);

    }

}
