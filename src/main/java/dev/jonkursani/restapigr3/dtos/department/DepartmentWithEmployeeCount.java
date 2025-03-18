package dev.jonkursani.restapigr3.dtos.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWithEmployeeCount {
    private Integer id;
    private String name;
    private String location;
    private int employeeCount;
}