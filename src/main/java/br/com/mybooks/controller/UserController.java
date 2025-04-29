package br.com.mybooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybooks.model.entity.UserEntity;
import br.com.mybooks.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/users")
@Tag(name = "Users", description = "Endpoits for Managing Users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Operation(summary = "Create a users", description = "Create a users", tags = { "Users" }, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthourized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity entity) {
        entity = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping
    @Operation(summary = "Find all users", description = "Find all users", tags = { "Users" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthourized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<UserEntity>> list() {
        List<UserEntity> list = service.list();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
