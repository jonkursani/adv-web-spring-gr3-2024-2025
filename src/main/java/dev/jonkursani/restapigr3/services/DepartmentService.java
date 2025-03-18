package dev.jonkursani.restapigr3.services;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findById(Integer id);
    DepartmentDto create(CreateDepartmentRequest request);
    DepartmentDto update(Integer id, UpdateDepartmentRequest request);
    void delete(Integer id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}