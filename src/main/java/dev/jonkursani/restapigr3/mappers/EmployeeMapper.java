package dev.jonkursani.restapigr3.mappers;

import dev.jonkursani.restapigr3.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr3.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    // source: entity = Employee -> target: dto = EmployeeDto
    EmployeeDto toDto(Employee employee);
}