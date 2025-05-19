package br.com.mybooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.model.Person;
import br.com.mybooks.model.dto.AccountCredentialsDTO;
import br.com.mybooks.model.dto.TokenDTO;
import br.com.mybooks.model.entity.PersonEntity;
import br.com.mybooks.model.mapper.PersonMapper;
import br.com.mybooks.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Login with user", description = "This endpoint logs in users and returns the token to be used in the other API endpoints.", tags = "Authentication")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AccountCredentialsDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(data));
    }

    @Operation(summary = "Refresh token", description = "This endpoint refreshes the token that has expired", tags = "Authentication")
    @GetMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(@RequestParam(value = "username") String username,
            @RequestHeader("Authorization") String refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(username, refreshToken));
    }

    @PostMapping("/register")
    @Operation(summary = "Register users", description = "This endpoint creates new users", tags = "Authentication")
    public ResponseEntity<Person> register(@RequestBody @Valid Person model) {
        PersonEntity entity = authService.register(PersonMapper.MAPPER.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonMapper.MAPPER.toModel(entity));
    }

}
