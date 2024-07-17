package com.dev.hakeem.myfinanceapp.entity;

import com.dev.hakeem.myfinanceapp.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
@Table(name = "tb_users")
public class User implements Serializable {
    private static final long serialVersionUID= 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não deve estar em branco")
    private String name;

    @NotBlank(message = "Email não deve estar em branco")
    @Email(message = "Formato do email está inválido")
    private String email;

    @NotBlank(message = "Senha não deve estar em branco")
    @Size(min = 6, max = 6, message = "A senha deve ter exatamente 6 caracteres")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 23)
    private Role role;








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
