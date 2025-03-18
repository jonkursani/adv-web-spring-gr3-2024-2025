package dev.jonkursani.restapigr3.repositories;

import dev.jonkursani.restapigr3.entities.Department;
import dev.jonkursani.restapigr3.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByDepartment(Department department);
    boolean existsEmployeeByEmail(String email);
}