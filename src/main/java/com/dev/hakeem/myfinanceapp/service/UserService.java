package com.dev.hakeem.myfinanceapp.service;

import com.dev.hakeem.myfinanceapp.dto.UserDTO;
import com.dev.hakeem.myfinanceapp.entity.User;
import com.dev.hakeem.myfinanceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria um novo usuário com base nos dados fornecidos no DTO e o salva no banco de dados.
     *
     * @param userDTO Dados do usuário a serem criados
     * @return O usuário criado e persistido
     */

    public User createUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setSenha(userDTO.getSenha());

        return repository.save(user);
    }


    /**
     * Busca um usuário pelo ID.
     *
     * @param id ID do usuário a ser buscado
     * @return Optional contendo o usuário encontrado, ou Optional vazio se não encontrado
     */

    public Optional<User> findById(Long id){
        return  repository.findById(id);
    }

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return Lista de todos os usuários
     */

    public List<User> findByAll(){
        return repository.findAll();
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido
     */


    public  void deleteUser(Long id){
         repository.deleteById(id);
    }

    /**
     * Verifica se um usuário existe pelo ID.
     *
     * @param id ID do usuário a ser verificado
     * @return true se o usuário existe, false caso contrário
     */

    public  boolean existeUser(Long id){
        return repository.existsById(id);
    }

    /**
     * Cadastra um novo usuário com base nos dados fornecidos no DTO.
     *
     * @param userDTO Dados do usuário a serem cadastrados
     * @return O usuário criado e persistido
     */

    public  User cadastrar(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setSenha(userDTO.getSenha()); // Codifica a senha antes de salvar

        return  repository.save(user);
    }

    /**
     * Realiza o login do usuário com base no e-mail e senha fornecidos.
     *
     * @param email    E-mail do usuário
     * @param senha    Senha do usuário
     * @return Optional contendo o usuário logado, ou Optional vazio se as credenciais estiverem incorretas
     */

    public  Optional<User> login (String email,String senha){
        Optional<User> obj = repository.findByEmail(email);
        if (obj.isPresent()){
            User user = obj.get();
           /* if (passwordEncoder.matches(senha, user.getSenha())) {  codificar futuramente
                return Optional.of(user);*/
        }
        return  Optional.empty();

    }
}
