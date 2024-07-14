package com.dev.hakeem.myfinanceapp.dto.userdto;

import com.dev.hakeem.myfinanceapp.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UpdateUserDTO {
    @NotBlank
    @Size(min = 6,max = 6)
    private String senha;
    @Size(min = 6,max = 6)
    private String senhaNova;
    @NotBlank
    @Size(min = 6,max = 6)
    private String confirmasenha;

}
