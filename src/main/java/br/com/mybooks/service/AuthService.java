package br.com.mybooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mybooks.auth.impl.JwtTokenProviderImpl;
import br.com.mybooks.exception.InvalidJwtAuthenticationException;
import br.com.mybooks.exception.RequiredIsNullException;
import br.com.mybooks.exception.UserExistsException;
import br.com.mybooks.model.dto.AccountCredentialsDTO;
import br.com.mybooks.model.dto.TokenDTO;
import br.com.mybooks.model.entity.PersonEntity;
import br.com.mybooks.model.entity.UserEntity;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProviderImpl tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

    public TokenDTO login(final AccountCredentialsDTO data) {
        try {
            final String username = data.getUsername();
            final String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final UserEntity user = userService.findByUsername(username);

            if (user != null) {
                final TokenDTO tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
                if (tokenResponse == null)
                    throw new InvalidJwtAuthenticationException("Não foi possível obter JWT Token para este usuário!");
            
                return tokenResponse;
            }
            throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado!");
        } catch (Exception e) {
            throw new BadCredentialsException("Usuário ou Senha inválidos!");
        }
    }

    public TokenDTO refreshToken(final String username, final String refreshToken) {
        if (checkIfParamsIsNull(username, refreshToken)) 
            throw new RequiredIsNullException("Os parâmetros não podem estar vazios!");
        
        final UserEntity user = userService.findByUsername(username);

        if (user != null) {
            final TokenDTO tokenResponse = tokenProvider.refreshToken(refreshToken);
            if (tokenResponse == null)
                throw new InvalidJwtAuthenticationException("Não foi possível obter JWT Token para este usuário!");
        
            return tokenResponse;
        }
        throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado!");
    }

    public PersonEntity register(final PersonEntity entity) {
        final UserEntity userEntityDB = userService.findByUsername(entity.getUser().getUsername());
        if (userEntityDB != null) 
            throw new UserExistsException("Este nome de usuário já existe!");
        final UserEntity userEntity = userService.create(entity.getUser());
        entity.setUser(userEntity);
        return personService.create(entity);
    }

    public Boolean checkIfParamsIsNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

}
