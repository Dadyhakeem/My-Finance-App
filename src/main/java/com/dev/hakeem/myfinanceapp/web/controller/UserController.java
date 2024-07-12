package com.dev.hakeem.myfinanceapp.web.controller;

import com.dev.hakeem.myfinanceapp.dto.UserDTO;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User>  CreateUser(@RequestBody UserDTO userDTO){
        User user = service.cadastrar(userDTO);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletarUser(@PathVariable Long id) {
        service.findById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<User> user = service.findById(id);
        return user.map(value -> ResponseEntity.ok(service.mapToDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public  ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> user = service.findAll();
        return ResponseEntity.ok(user);
    }


}
