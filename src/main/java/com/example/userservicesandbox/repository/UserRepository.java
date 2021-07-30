package com.example.userservicesandbox.repository;

import com.example.userservicesandbox.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
