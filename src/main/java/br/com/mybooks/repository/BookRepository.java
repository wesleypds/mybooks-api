package br.com.mybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mybooks.model.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
