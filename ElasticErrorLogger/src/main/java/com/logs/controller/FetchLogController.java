package com.logs.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.logs.model.LogEntry;

@RestController
@RequestMapping("/logs")
public class FetchLogController {

	private final ElasticsearchClient client;

	public FetchLogController(ElasticsearchClient client) {
		this.client = client;
	}

	@GetMapping
	public List<LogEntry> getLogs() throws Exception {

		SearchResponse<LogEntry> response = client.search(s -> s.index("logs").query(q -> q.matchAll(m -> m)),
				LogEntry.class);

		return response.hits().hits().stream().map(hit -> hit.source()).toList();
	}

}