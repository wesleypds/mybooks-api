package br.com.mybooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mybooks.auth.impl.JwtTokenProviderImpl;
import br.com.mybooks.model.dto.AccountCredentialsDTO;
import br.com.mybooks.model.dto.TokenDTO;
import br.com.mybooks.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProviderImpl tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity login(AccountCredentialsDTO data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenDTO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password suplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);

        var tokenResponse = new TokenDTO();

        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

    public Boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    public Boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }

}
