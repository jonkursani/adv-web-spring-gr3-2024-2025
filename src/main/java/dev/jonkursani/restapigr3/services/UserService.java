package dev.jonkursani.restapigr3.services;

import dev.jonkursani.restapigr3.dtos.user.CreateUserRequest;
import dev.jonkursani.restapigr3.dtos.user.UpdateUserRequest;
import dev.jonkursani.restapigr3.dtos.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(int id);
    UserDto create(CreateUserRequest request);
    void update(int id, UpdateUserRequest request);
    void delete(int id);
}
