package dev.jonkursani.restapigr3.services;

import dev.jonkursani.restapigr3.dtos.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
}