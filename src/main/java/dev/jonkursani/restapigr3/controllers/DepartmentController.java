package dev.jonkursani.restapigr3.controllers;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr3.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Validated
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DepartmentDto> create(
            @RequestPart("data") @Valid CreateDepartmentRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
//        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED); // 201
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, imageFile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> update(
            @PathVariable Integer id,
            @RequestPart("data") @Valid UpdateDepartmentRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        return ResponseEntity.ok(service.update(id, request, imageFile));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/employee-count")
    public ResponseEntity<List<DepartmentWithEmployeeCount>> findAllWithEmployeeCount() {
        return ResponseEntity.ok(service.findAllWithEmployeeCount());
    }
}
