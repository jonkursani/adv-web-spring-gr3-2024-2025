package dev.jonkursani.restapigr3.controllers;

import dev.jonkursani.restapigr3.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr3.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll(@RequestParam(required = false) Integer departmentId) {
        return ResponseEntity.ok(service.findAll(departmentId));
    }
}