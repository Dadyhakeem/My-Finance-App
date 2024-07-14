package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.userdto.CreateUserDTO;
import com.dev.hakeem.myfinanceapp.dto.userdto.UpdateUserDTO;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.service.UserService;
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

    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody CreateUserDTO createUserDTO){
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
