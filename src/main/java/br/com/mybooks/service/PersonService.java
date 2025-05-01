package br.com.mybooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybooks.model.entity.PersonEntity;
import br.com.mybooks.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonEntity create(PersonEntity entity) {
        return repository.save(entity);
    }

}
