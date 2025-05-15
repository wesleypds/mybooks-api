package br.com.mybooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    private JwtTokenProviderService tokenProvider;

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
                    throw new InvalidJwtAuthenticationException("Unable to get JWT Token for this user!");
            
                return tokenResponse;
            }
            throw new UsernameNotFoundException(String.format("User %s not found!", username));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    public TokenDTO refreshToken(final String username, final String refreshToken) {
        if (checkIfParamsIsNull(username, refreshToken)) 
            throw new RequiredIsNullException("Parameters cannot be empty!");
        
        final UserEntity user = userService.findByUsername(username);

        if (user != null) {
            final TokenDTO tokenResponse = tokenProvider.refreshToken(refreshToken);
            if (tokenResponse == null)
                throw new InvalidJwtAuthenticationException("Unable to get JWT Token for this user!");
        
            return tokenResponse;
        }
        throw new UsernameNotFoundException(String.format("User %s not found!", username));
    }

    public PersonEntity register(final PersonEntity entity) {
        final UserEntity userEntityDB = userService.findByUsername(entity.getUser().getUsername());
        if (userEntityDB != null) 
            throw new UserExistsException("This username already exists!");
        final UserEntity userEntity = userService.create(entity.getUser());
        entity.setUser(userEntity);
        return personService.create(entity);
    }

    public Boolean checkIfParamsIsNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

}
