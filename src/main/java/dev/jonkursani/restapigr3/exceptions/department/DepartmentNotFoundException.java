package dev.jonkursani.restapigr3.exceptions.department;

import dev.jonkursani.restapigr3.exceptions.ResourceNotFoundException;

public class DepartmentNotFoundException extends ResourceNotFoundException {
    public DepartmentNotFoundException(Integer id) {
        super("Department with id " + id + " not found");
    }
}
