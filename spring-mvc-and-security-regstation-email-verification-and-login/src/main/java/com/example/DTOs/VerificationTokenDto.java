package com.example.DTOs;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "users_verification_tokens")
public class VerificationTokenDto {

    private static final int EXPIRATION = 86400000;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private String token;

    @OneToOne(targetEntity = UserDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserDto user;

    private Long exp;

    public VerificationTokenDto() {
        exp = new Date().getTime() + EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}
