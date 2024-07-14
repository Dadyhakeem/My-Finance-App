package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.userdto.CreateUserDTO;
import com.dev.hakeem.myfinanceapp.dto.userdto.UpdateUserDTO;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Método auxiliar para mapear User para CreateUserDTO
    @Transactional
    public CreateUserDTO mapToDTO(User user) {
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public List<CreateUserDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public boolean existsUser(Long id) {
        return repository.existsById(id);
    }
    @Transactional
    public User cadastrar(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setSenha(createUserDTO.getSenha());
        user.setRole(createUserDTO.getRole());
        // Não é necessário setar address e dateOfBirth, pois não estão na entidade User
        return repository.save(user);
    }
    @Transactional
    public Optional<User> login(String email, String senha) {
        Optional<User> obj = repository.findByEmail(email);
        if (obj.isPresent()) {
            User user = obj.get();
            // if (passwordEncoder.matches(senha, user.getSenha())) {  // Codificar futuramente
            //     return Optional.of(user);
            // }
        }
        return Optional.empty();
    }

    /**
     * Atualiza a senha de um usuário.
     *
     * @param id             ID do usuário cuja senha será atualizada
     * @param updateUserDTO  DTO contendo a senha atual, nova senha e confirmação de senha
     * @throws RuntimeException se o usuário não for encontrado
     * @throws IllegalArgumentException se a senha atual estiver incorreta ou a nova senha não corresponder à confirmação
     */
    @Transactional
    public void atualizarSenha(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User user = obj.get();

        // Verifica se a senha atual está correta
        if (!user.getSenha().equals(updateUserDTO.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }

        // Verifica se a nova senha e a confirmação correspondem
        if (!updateUserDTO.getSenhaNova().equals(updateUserDTO.getConfirmasenha())) {
            throw new IllegalArgumentException("Nova senha e confirmação não correspondem.");
        }

        user.setSenha(updateUserDTO.getSenhaNova());
        repository.save(user);
    }
}
