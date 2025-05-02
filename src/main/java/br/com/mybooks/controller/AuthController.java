package br.com.mybooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.model.Person;
import br.com.mybooks.model.dto.AccountCredentialsDTO;
import br.com.mybooks.model.dto.TokenDTO;
import br.com.mybooks.model.entity.PersonEntity;
import br.com.mybooks.model.mapper.PersonMapper;
import br.com.mybooks.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Logar com usuário", description = "Este endpoint loga usuários e retorna o token para ser usado nos outros endpoints da API", tags = "Autenticação")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AccountCredentialsDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(data));
    }

    @Operation(summary = "Atualizar token", description = "Este endpoint atualiza o token que foi expirado", tags = "Autenticação")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/refresh-token/{username}")
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable(value = "username") String username,
            @RequestHeader("Authorization") String refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(username, refreshToken));
    }

    @PostMapping("/register")
    @Operation(summary = "Criar usuários", description = "Este endpoint cria novos usuários", tags = "Autenticação")
    public ResponseEntity<Person> create(@RequestBody @Valid Person model) {
        PersonEntity entity = authService.register(PersonMapper.MAPPER.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonMapper.MAPPER.toModel(entity));
    }

}
