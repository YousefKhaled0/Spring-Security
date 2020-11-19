package com.example.repos;

import com.example.DTOs.VerificationTokenDto;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<VerificationTokenDto , Long> {
    VerificationTokenDto findByToken(String token);
}
