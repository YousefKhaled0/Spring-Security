package com.example.repos;

import com.example.DTOs.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserDto , Long> {
     UserDto findByEmail(String email);
}


