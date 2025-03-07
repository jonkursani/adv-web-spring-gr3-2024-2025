//package dev.jonkursani.restapigr3.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "employees")
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Integer id;
//
//    @Size(max = 50)
//    @NotNull
//    @Column(nullable = false, length = 50, name = "first_name")
//    private String firstName;
//
//    @Size(max = 50)
//    @NotNull
//    @Column(nullable = false, length = 50, name = "last_name")
//    private String lastName;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @Column(name = "hire_date")
//    private LocalDate hireDate;
//
//    @Size(max = 100)
//    @Column(length = 100, unique = true)
//    private String email;
//}
