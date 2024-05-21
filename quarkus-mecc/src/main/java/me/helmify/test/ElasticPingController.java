package me.helmify.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.util.Map;

@Path("/ping")
public class ElasticPingController {

    @Inject
    ElasticsearchClient client;

    public static class ElasticsearchEntity {
        public String id;
        public String name;
    }

    @GET
    @Path("/elasticsearch")
    public Map<String, Object> hello() throws Exception {
        String elasticsearch = "elasticsearch";

        ElasticsearchEntity elasticsearchEntity = new ElasticsearchEntity();
        elasticsearchEntity.id = "1";
        elasticsearchEntity.name = "elasticsearch";
        IndexRequest<ElasticsearchEntity> request = IndexRequest.of(
                b -> b.index("words")
                        .id(elasticsearchEntity.id)
                        .document(elasticsearchEntity));
        client.index(request);

        // write
        GetRequest getRequest = GetRequest.of(
                b -> b.index("words")
                        .id(elasticsearchEntity.id));
        GetResponse<ElasticsearchEntity> getResponse = client.get(getRequest, ElasticsearchEntity.class);

        return Map.of(elasticsearch, getResponse != null && elasticsearch.equals(getResponse.source().name));
    }
}
