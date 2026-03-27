package com.logs.config;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

	@Value("${spring.elasticsearch.uris}")
	private String elasticUri;
	
	@Value("${spring.elasticsearch.api-key}")
	private String apiKey;

	@Bean
	public ElasticsearchClient elasticsearchClient() {
		RestClient restClient = RestClient.builder(HttpHost.create(elasticUri))
				.setDefaultHeaders(new org.apache.http.Header[] {
						new org.apache.http.message.BasicHeader("Authorization", "ApiKey " + apiKey)
				})
				.build();
		RestClientTransport transport = new RestClientTransport(
				restClient,
				new JacksonJsonpMapper()
		);
		return new ElasticsearchClient(transport);
	}
}
