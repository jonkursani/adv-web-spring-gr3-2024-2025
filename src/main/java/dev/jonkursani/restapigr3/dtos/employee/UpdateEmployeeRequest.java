package dev.jonkursani.restapigr3.dtos.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than {max} characters")
    private String lastName;

    private int departmentId;

    private LocalDate hireDate;

    @Size(max = 100, message = "Email must be less than {max} characters")
    @Email(message = "Email must be valid")
    private String email;
}