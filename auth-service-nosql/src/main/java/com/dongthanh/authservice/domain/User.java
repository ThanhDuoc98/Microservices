package com.dongthanh.authservice.domain;

import com.dongthanh.authservice.enums.Authorities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private boolean activated;

    private String activationKey;

    private String resetPasswordKey;

    private Set<Authorities> authorities = new HashSet<>();
}
