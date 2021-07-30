package com.example.userservicesandbox.controllers;

import com.example.userservicesandbox.domain.Log;
import com.example.userservicesandbox.domain.LogDTO;
import com.example.userservicesandbox.domain.User;
import com.example.userservicesandbox.domain.UserDTO;
import com.example.userservicesandbox.repository.UserRepository;
import com.example.userservicesandbox.services.IntegrationService;
import com.example.userservicesandbox.services.LogProducerService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static String CREATION_MESSAGE = "User created account";
    private final static String ENTERING_MESSAGE = "User logged in";

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LogProducerService logProducerService;

    @Autowired
    IntegrationService integrationService;

    @PostMapping
    private void createUser(@RequestBody UserDTO newUser){
        User user = modelMapper.map(newUser, User.class);
        userRepository.save(user);
        userRepository.findAll().forEach(System.err::println);
        logProducerService.produce(new LogDTO(user.get_id(), CREATION_MESSAGE));
    }

    @PostMapping("/logIn")
    private void logIn(@RequestParam String email){
        var userId = userRepository.findByEmail(email).get_id();
        logProducerService.produce(new LogDTO(userId, ENTERING_MESSAGE));
    }

    @GetMapping("/userHistory")
    private List<Log> getLogs(@RequestParam String email){
        var userId = userRepository.findByEmail(email).get_id();
        return integrationService.fetchUsersLogs(userId);
    }

}
