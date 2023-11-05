package com.jsh.newsp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @NonNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "Password", nullable = false)
    private String password;
}
