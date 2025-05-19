package br.com.mybooks.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.model.Book;
import br.com.mybooks.model.mapper.BookMapper;
import br.com.mybooks.service.PersonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping(value = "/add-book/{idPerson}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> addBookInPerson(@PathVariable(value = "idPerson") Long idPerson, @RequestPart Book book, @RequestPart MultipartFile file) throws IOException {
        service.addBookInPerson(idPerson, BookMapper.MAPPER.toEntity(book), file);
        return ResponseEntity.status(HttpStatus.CREATED).body("successus");
    } 

}
