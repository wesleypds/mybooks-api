package br.com.mybooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(String.format("User %s not found!", username));
    }

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
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password.subSequence(0, password.length()));
    }

}
