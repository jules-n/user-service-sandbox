package com.example.userservicesandbox.services;

import com.example.userservicesandbox.domain.Log;
import org.bson.types.ObjectId;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IntegrationService {
    private static final String URL_LOGS = "http://localhost:8080/logs";
    private final RestTemplate restTemplate;

    public IntegrationService(){
        restTemplate = new RestTemplate();
    }

    public List<Log> fetchUsersLogs(ObjectId _id){
        return restTemplate.getForObject(URL_LOGS+"/?_id={_id}", List.class, _id);
    }
}
