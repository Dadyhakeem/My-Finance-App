package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.userdto.CreateUserDTO;
import com.dev.hakeem.myfinanceapp.dto.userdto.UpdateUserDTO;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.service.UserService;
import com.dev.hakeem.myfinanceapp.web.controller.exception.ErroMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @Operation(summary = "Criar um novo Usuario",description = "Recourso pra criar um novo usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recourso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "409", description = "Usuariio E-mail ja cadastrado no sistema ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroMessage.class))),
                     @ApiResponse(responseCode = "422", description = "Recourso nao processada por dados de entrada envalidos ",
                                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroMessage.class)))


            }
    )



    @PostMapping
    public ResponseEntity<User> CreateUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        User user = service.cadastrar(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUser(@PathVariable Long id) {
        if (service.existsUser(id)) {
            service.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserDTO> findById(@PathVariable Long id) {
        Optional<User> user = service.findById(id);
        return user.map(value -> ResponseEntity.ok(service.mapToDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public  ResponseEntity<List<CreateUserDTO>> findAll(){
        List<CreateUserDTO> user = service.findAll();
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}/update-senha")
    public ResponseEntity<Void> updateSenha(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        service.atualizarSenha(id, updateUserDTO);
        return ResponseEntity.noContent().build();
    }


}
