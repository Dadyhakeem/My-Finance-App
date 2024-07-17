package com.dev.hakeem.myfinanceapp.dto.userdto;

import com.dev.hakeem.myfinanceapp.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @NotBlank
    @Email(message = "Formato do email está inválido")
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    private String senha;

    private Role role;
    private Long id;
}
