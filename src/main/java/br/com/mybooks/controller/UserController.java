package br.com.mybooks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.model.User;
import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.model.mapper.UserMapper;
import br.com.mybooks.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/users")
@Tag(name = "Usuários", description = "Endpoits for Managing Users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Operation(summary = "Cria usuários", description = "Este endpoint cria usuários", tags = {
            "Usuários" }, responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthourized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<User> create(@RequestBody User model) {
        UserEntity entity = service.create(UserMapper.MAPPER.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.MAPPER.toModel(entity));
    }

    @GetMapping
    @Operation(summary = "Find all users", description = "Find all users", tags = { "Usuários" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthourized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<User>> list() {
        List<UserEntity> listEntities = service.list();
        List<User> list = listEntities.stream()
                .map(u -> UserMapper.MAPPER.toModel(u))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
