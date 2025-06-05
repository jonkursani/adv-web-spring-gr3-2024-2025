package dev.jonkursani.restapigr3.services.impls;

import dev.jonkursani.restapigr3.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr3.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr3.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr3.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr3.exceptions.department.DepartmentHasEmployeesException;
import dev.jonkursani.restapigr3.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr3.mappers.DepartmentMapper;
import dev.jonkursani.restapigr3.repositories.DepartmentRepository;
import dev.jonkursani.restapigr3.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
                        new DepartmentDto(department.getId(), department.getName(), department.getLocation(), department.getImagePath()))
                .toList();
    }

    @Override
    public DepartmentDto findById(Integer id) {
        return repository.findById(id)
//                .map(department -> mapper.toDto(department))
                .map(mapper::toDto)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request, MultipartFile imageFile) {
        var fileName = "";
        if (imageFile != null && !imageFile.isEmpty()) fileName = uploadFile(imageFile);
        var department = mapper.toEntity(request);
        department.setImagePath(fileName);
        var createdDepartment = repository.save(department);
        return mapper.toDto(createdDepartment);
    }

    @Override
    public DepartmentDto update(Integer id, UpdateDepartmentRequest request, MultipartFile imageFile) {
        var departmentFromDb = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        mapper.updateEntityFromDto(request, departmentFromDb);

        var fileName = "";
        if (imageFile != null && !imageFile.isEmpty())
            fileName = uploadFile(imageFile);

        if (fileName.isBlank())
            departmentFromDb.setImagePath(departmentFromDb.getImagePath());
        else
            departmentFromDb.setImagePath(fileName);

        var updatedDepartment = repository.save(departmentFromDb);
        return mapper.toDto(updatedDepartment);
    }

    public String uploadFile(MultipartFile imageFile) {
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir); // Create uploads/ if it doesn't exist
            Path imagePath = uploadDir.resolve(filename);
            Files.write(imagePath, imageFile.getBytes());
            return "uploads/" + filename;  // Return the path to the image file
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }

    @Override
    public void delete(Integer id) {
        var departmentFromDb = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        if (!departmentFromDb.getEmployees().isEmpty()) {
            throw new DepartmentHasEmployeesException(id);
        }

//        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<DepartmentWithEmployeeCount> findAllWithEmployeeCount() {
        return repository.findAllWithEmployeeCount()
                .stream()
                .map(mapper::toDepartmentWithEmployeeCount)
                .toList();
    }
}