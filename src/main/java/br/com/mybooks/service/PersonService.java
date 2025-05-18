package br.com.mybooks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.exception.ResourceNotFoundException;
import br.com.mybooks.model.entity.BookEntity;
import br.com.mybooks.model.entity.PersonEntity;
import br.com.mybooks.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonEntity create(PersonEntity entity) {
        return repository.save(entity);
    }

    public void addBookInPerson(Long idPerson, BookEntity book, MultipartFile file) {
        Optional<PersonEntity> db = repository.findById(idPerson);
        if (db.isEmpty()) new ResourceNotFoundException("Person not found!");

        PersonEntity entity = db.get();

        entity.getBooks().add(book);
    }

}
