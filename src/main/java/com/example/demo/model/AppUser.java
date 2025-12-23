package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email is not valid")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    private String role;
}