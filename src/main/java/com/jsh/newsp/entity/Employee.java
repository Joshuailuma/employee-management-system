package com.jsh.newsp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_data")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Job cannot be blank")
    @NonNull
    @Column(name = "job", nullable = false)
    private String job;

    @NotBlank(message = "Department cannot be blank")
    @NonNull
    @Column(name = "department", nullable = false)
    private String department;

    @NotBlank(message = "Phone number cannot be blank")
    @NonNull
    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @NotBlank(message = "Address cannot be blank")
    @NonNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Employee status cannot be blank")
    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean employeeStatus;
}
