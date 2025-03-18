package dev.jonkursani.restapigr3.controllers;

import dev.jonkursani.restapigr3.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr3.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr3.dtos.employee.UpdateEmployeeRequest;
import dev.jonkursani.restapigr3.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping // /api/v1/employees?departmentId=1
    public ResponseEntity<List<EmployeeDto>> findAll(@RequestParam(required = false) Integer departmentId) {
        return ResponseEntity.ok(service.findAll(departmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody CreateEmployeeRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id,
                                              @Valid @RequestBody UpdateEmployeeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }







}