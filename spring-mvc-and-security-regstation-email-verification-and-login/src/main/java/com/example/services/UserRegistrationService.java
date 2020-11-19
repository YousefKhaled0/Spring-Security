package com.example.services;

import com.example.DTOs.UserDto;
import com.example.DTOs.VerificationTokenDto;
import com.example.repos.TokenRepo;
import com.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean register(UserDto userDto) {
        String email = userDto.getEmail();
        UserDto byEmail = userRepo.findByEmail(email);
        if (byEmail == null) {
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userRepo.save(userDto);
            return true;
        }
        return false;
    }

    public String generateToken(UserDto userDto){
        String token = UUID.randomUUID().toString();
        token = token.replaceAll("-", "");
        VerificationTokenDto verificationTokenDto = new VerificationTokenDto();
        verificationTokenDto.setToken(token);
        verificationTokenDto.setUser(userDto);
        tokenRepo.save(verificationTokenDto);
        return token;
    }
}
