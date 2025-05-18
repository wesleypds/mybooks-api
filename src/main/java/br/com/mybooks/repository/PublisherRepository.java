package br.com.mybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mybooks.model.entity.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

}
