//package com.assesment.ems.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "employees")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is required")
//    @Column(nullable = false)
//    private String name;
//
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @NotBlank(message = "Department is required")
//    @Column(nullable = false)
//    private String department;
//
//    @NotBlank(message = "Designation is required")
//    @Column(nullable = false)
//    private String designation;
//
//    @NotNull(message = "Joining date is required")
//    @Column(name = "joining_date", nullable = false)
//    private LocalDate joiningDate;
//
//    @NotNull(message = "Salary is required")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
//    @Column(nullable = false)
//    private BigDecimal salary;
//
//
//}

package com.assesment.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Designation is required")
    private String designation;

//    @NotNull(message = "Joining date is required")
//    private LocalDate joiningDate;
@NotNull(message = "Joining date is required")
@Temporal(TemporalType.DATE)
private Date joiningDate;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private Double salary;
}
