package br.com.mybooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.model.User;
import br.com.mybooks.model.dto.AccountCredentialsDTO;
import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.model.mapper.UserMapper;
import br.com.mybooks.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Logar com usuário", description = "Este endpoint loga usuários e retorna o token para ser usado nos outros endpoints da API", tags = "Autenticação")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AccountCredentialsDTO data) {
        if (authService.checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        var token = authService.login(data);

        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Atualizar token", description = "Este endpoint atualiza o token que foi expirado", tags = "Autenticação")
    @PutMapping("/refresh-token")
    public ResponseEntity refreshToken(@PathParam(value = "username") String username,
            @RequestHeader("Authorization") String refreshToken) {
        if (authService.checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        var token = authService.refreshToken(username, refreshToken);

        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    // @PostMapping("/register")
    // @Operation(summary = "Cria usuários", description = "Este endpoint cria novos
    // usuários", tags = "Autenticação")
    // public ResponseEntity<User> create(@RequestBody User model) {
    // UserEntity entity = service.create(UserMapper.MAPPER.toEntity(model));
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.MAPPER.toModel(entity));
    // }

}
