package dev.jonkursani.restapigr3.services.impls;

import dev.jonkursani.restapigr3.dtos.CreateUserRequest;
import dev.jonkursani.restapigr3.dtos.UpdateUserRequest;
import dev.jonkursani.restapigr3.dtos.UserDto;
import dev.jonkursani.restapigr3.entities.User;
import dev.jonkursani.restapigr3.repositories.UserRepository;
import dev.jonkursani.restapigr3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // Kjo bohet inject duke perdor @RequiredArgsConstructor
    private final UserRepository repository;

//    @Autowired
//    public UserServiceImpl(UserRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .toList();
    }

    @Override
    public UserDto findById(int id) {
        return repository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        var userToCreate = new User(0, request.getName(), request.getEmail());
        var createdUser = repository.save(userToCreate);
        return new UserDto(createdUser.getId(), createdUser.getName(), createdUser.getEmail());
    }

    @Override
    public void update(int id, UpdateUserRequest request) {
        var userToUpdate = findById(id);
        if (userToUpdate != null) {
            var updatedUser = new User(id, request.getName(), request.getEmail());
            repository.save(updatedUser);
        }
    }

    @Override
    public void delete(int id) {
        var userToDelete = findById(id);
        if (userToDelete != null) {
            repository.deleteById(id);
        }
    }
}
