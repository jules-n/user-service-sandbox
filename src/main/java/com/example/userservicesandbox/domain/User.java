package com.example.userservicesandbox.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = User.COLLECTION_NAME)
public class User {
    public static final String COLLECTION_NAME = "users";

    private ObjectId _id;
    private String name;
    private String email;
}
