package dev.jonkursani.restapigr3.repositories;

import dev.jonkursani.restapigr3.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}