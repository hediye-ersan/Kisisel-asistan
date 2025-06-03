package com.example.Kisisel_Asistan.Auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthenticationRequest {
    private String username;
    private String password;
}
