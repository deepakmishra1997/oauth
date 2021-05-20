package com.jdbl_12_oauth.oauth2.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    String userName;
    String  password;
}

