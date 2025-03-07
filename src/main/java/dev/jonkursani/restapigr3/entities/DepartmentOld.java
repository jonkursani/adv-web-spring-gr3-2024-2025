//package dev.jonkursani.restapigr3.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "departments")
//public class Department {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Integer id;
//
//    @Size(max = 100)
//    @NotNull
//    @Column(nullable = false, length = 100)
//    private String name;
//
//    @Size(max = 100)
//    @Column(length = 100)
//    private String location;
//
//    @OneToMany(mappedBy = "department")
//    private List<Employee> employees;
//}