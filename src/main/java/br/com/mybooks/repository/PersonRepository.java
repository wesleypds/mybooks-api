package br.com.mybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mybooks.model.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
