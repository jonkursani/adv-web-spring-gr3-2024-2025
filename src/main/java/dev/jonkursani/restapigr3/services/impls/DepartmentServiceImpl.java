package dev.jonkursani.restapigr3.services.impls;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr3.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr3.mappers.DepartmentMapper;
import dev.jonkursani.restapigr3.repositories.DepartmentRepository;
import dev.jonkursani.restapigr3.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(department ->
                        new DepartmentDto(department.getId(), department.getName(), department.getLocation()))
                .toList();
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request) {
        var department = mapper.toEntity(request);
        var createdDepartment = repository.save(department);
        return mapper.toDto(createdDepartment);
    }

    @Override
    public DepartmentDto update(Integer id, UpdateDepartmentRequest request) {
        var departmentFromDb = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        mapper.updateEntityFromDto(request, departmentFromDb);
        var updatedDepartment = repository.save(departmentFromDb);
        return mapper.toDto(updatedDepartment);
    }
}
