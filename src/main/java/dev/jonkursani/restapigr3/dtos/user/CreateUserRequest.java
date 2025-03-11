package dev.jonkursani.restapigr3.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
//    private int createdBy;
//    private LocalDateTime createdAt;
}
