package dev.jonkursani.restapigr3.services;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findById(Integer id);
    DepartmentDto create(CreateDepartmentRequest request, MultipartFile imageFile);
    DepartmentDto update(Integer id, UpdateDepartmentRequest request, MultipartFile imageFile);
    void delete(Integer id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}