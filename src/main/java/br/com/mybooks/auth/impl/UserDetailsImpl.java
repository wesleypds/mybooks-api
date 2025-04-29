package br.com.mybooks.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.repository.UserRepository;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(String.format("Usuário %s não encontrado!", username));
    }

}
