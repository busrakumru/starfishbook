package de.beuth.starfishbook.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;

    private String password;

    private String verificationCode;

    private boolean enabled;

}
