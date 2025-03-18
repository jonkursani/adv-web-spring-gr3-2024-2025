package dev.jonkursani.restapigr3.mappers;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr3.entities.Department;
import dev.jonkursani.restapigr3.entities.Employee;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(CreateDepartmentRequest request);
    // source: dto = DepartmentDto -> target: entity = Department
    Department toEntity(DepartmentDto request);
    void updateEntityFromDto(UpdateDepartmentRequest request, @MappingTarget Department department);

    // source: entity = Department -> target: dto = DepartmentWithEmployeeCount
    @Mapping(target = "employeeCount", source = "employees", qualifiedByName = "countEmployees")
    DepartmentWithEmployeeCount toDepartmentWithEmployeeCount(Department department);

    @Named("countEmployees")
    default int countEmployees(Set<Employee> employees) {
//        if (employees == null){
//            return 0;
//        }
//
//        return employees.size();

        return employees == null ? 0 : employees.size();
    }
}