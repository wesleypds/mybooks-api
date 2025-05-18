package br.com.mybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mybooks.model.entity.ReadingDataEntity;

public interface ReadingDataRepository extends JpaRepository<ReadingDataEntity, Long> {

}
