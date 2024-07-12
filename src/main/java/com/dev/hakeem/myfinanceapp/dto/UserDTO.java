package com.dev.hakeem.myfinanceapp.dto;

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
public class UserDTO {
    private  Long id;
    @NotBlank
    private  String name;
    @NotBlank
    private  String email;
    @NotBlank
    @Size(min = 6, max = 6)
    private  String senha;
}
