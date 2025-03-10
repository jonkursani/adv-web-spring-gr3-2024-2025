package dev.jonkursani.restapigr3.repositories;

import dev.jonkursani.restapigr3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}