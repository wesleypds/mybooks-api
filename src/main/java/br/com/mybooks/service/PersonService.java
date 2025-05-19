package br.com.mybooks.service;

import java.io.IOException;
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

    @Autowired
    private BookService bookService;

    public PersonEntity create(PersonEntity entity) {
        return repository.save(entity);
    }

    public void addBookInPerson(Long idPerson, BookEntity book, MultipartFile file) throws IOException {
        Optional<PersonEntity> db = repository.findById(idPerson);
        if (db.isEmpty()) new ResourceNotFoundException("Person not found!");
        PersonEntity entity = db.get();

        BookEntity bookPersisted = bookService.addBook(book, file, entity.getUser().getUsername());
        entity.getBooks().add(bookPersisted);
        repository.save(entity);
    }

}
