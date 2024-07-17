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

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Método auxiliar para mapear User para CreateUserDTO
    @Transactional(readOnly = true)
    public CreateUserDTO mapToDTO(User user) {
        return new CreateUserDTO(
                user.getName(),
                user.getEmail(),
                user.getSenha(),
                user.getRole(),
                user.getId()
        );
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CreateUserDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
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
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> login(String email, String senha) {
        Optional<User> obj = repository.findByEmail(email);
        if (obj.isPresent()) {
            User user = obj.get();
            // Aqui você poderia adicionar a lógica de verificação de senha usando passwordEncoder
            // if (passwordEncoder.matches(senha, user.getSenha())) {
            //     return Optional.of(user);
            // }
            if (user.getSenha().equals(senha)) {
                return Optional.of(user);
            }
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
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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
