package br.com.mybooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybooks.config.SecurityConfig;
import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityConfig securityConfig;

    public UserEntity create(UserEntity entity) {
        String passwordEncoded = encodePassword(entity.getPassword());
        entity.setPassword(passwordEncoded);
        return repository.save(entity);
    }

    public List<UserEntity> list() {
        return repository.findAll();
    }

    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }

    private String encodePassword(String password) {
        return securityConfig.passwordEncoder().encode(password.subSequence(0, password.length()));
    }

}
