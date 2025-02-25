package dev.jonkursani.restapigr3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String name;
    private String email;
//    private int updatedBy;
//    private LocalDateTime updatedAt;
}
