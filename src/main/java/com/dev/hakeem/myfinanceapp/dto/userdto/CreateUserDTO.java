package com.dev.hakeem.myfinanceapp.dto.userdto;

import com.dev.hakeem.myfinanceapp.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {

    @NotBlank
    private  String name;
    @NotBlank
    @Email(message = "Formato do email esta invalido",regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private  String email;
    @NotBlank
    @Size(min = 6, max = 6)
    private  String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false,length = 23)
    private Role role;
    private  Long id;
}
