package dev.jonkursani.restapigr3.repositories;

import dev.jonkursani.restapigr3.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // JPQL -> Java Persistence Query Language
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees")
//    @Query("SELECT d from Department d left join Employee e on d.id = e.department.id")
    List<Department> findAllWithEmployeeCount();
}