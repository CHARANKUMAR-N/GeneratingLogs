package com.logs.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticService {
 
    private final ElasticsearchClient client;
 
    public ElasticService(ElasticsearchClient client) {
        this.client = client;
    }
 
    public void saveLog(String service, String level, String message, Exception exception) throws Exception {
 
        StackTraceElement element = exception.getStackTrace()[0];
 
        String className = element.getClassName();
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();
 
        String errorType = exception.getClass().getSimpleName();
 
        Map<String, Object> log = new HashMap<>();
        log.put("service", service);
        log.put("className", className);
        log.put("methodName", methodName);
        log.put("lineNumber", lineNumber);
        log.put("level", level);
        log.put("message", message);
        log.put("exception", exception.toString());
        log.put("errorType", errorType);
        log.put("timestamp", Instant.now().toString());
 
        client.index(i -> i.index("logs").document(log));
    }
}