package com.dev.hakeem.myfinanceapp;

import com.dev.hakeem.myfinanceapp.dto.userdto.CreateUserDTO;
import com.dev.hakeem.myfinanceapp.enums.Role;
import com.dev.hakeem.myfinanceapp.web.controller.exception.ErroMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/user/user-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/user/user-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUser_ComEmailESenhaValidos_RetornaUserCriadoComStatus201() {
        CreateUserDTO createUserDTO = new CreateUserDTO("Frane", "frane@gmail.com", "123456", Role.ROLE_ADMIN, null);

        CreateUserDTO responseBody = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createUserDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreateUserDTO.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getName()).isEqualTo("Frane");
        assertThat(responseBody.getEmail()).isEqualTo("frane@gmail.com");
        assertThat(responseBody.getSenha()).isEqualTo("123456");
        assertThat(responseBody.getRole()).isEqualTo(Role.ROLE_ADMIN);
    }
    @Test
    public void createUser_ComEmailInvalidos_RetornaErroMessageStatus422() {
        // Teste para e-mail em branco
        ErroMessage responseBody = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CreateUserDTO("Frane", " ", "123456", Role.ROLE_ADMIN, null))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErroMessage.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getStatus()).isEqualTo(422);

        // Teste para formato de e-mail inválido
        responseBody = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CreateUserDTO("Frane", "frane@", "123456", Role.ROLE_ADMIN, null))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErroMessage.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getStatus()).isEqualTo(422);

        // Teste para formato de e-mail inválido
        responseBody = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CreateUserDTO("Frane", "franegmail.", "123456", Role.ROLE_ADMIN, null))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErroMessage.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getStatus()).isEqualTo(422);
    }

}
