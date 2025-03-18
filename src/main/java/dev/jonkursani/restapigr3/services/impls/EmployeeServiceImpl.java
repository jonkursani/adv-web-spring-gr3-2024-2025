package dev.jonkursani.restapigr3.services.impls;

import dev.jonkursani.restapigr3.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr3.mappers.DepartmentMapper;
import dev.jonkursani.restapigr3.mappers.EmployeeMapper;
import dev.jonkursani.restapigr3.repositories.EmployeeRepository;
import dev.jonkursani.restapigr3.services.DepartmentService;
import dev.jonkursani.restapigr3.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll(Integer departmentId) {
        if (departmentId != null) {
            // gjej departamentin me id prej department service
            var departmentDto = departmentService.findById(departmentId);
            // konverto departamenDto ne departament entity
            var department = departmentMapper.toEntity(departmentDto);
            // konverto employee entity ne employee dto
            return repository.findAllByDepartment(department)
                    .stream()
//                    .map(employee -> employeeMapper.toDto(employee))
                    .map(employeeMapper::toDto)
                    .toList();
        } else {
            return repository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }
}