package com.texo.repository;

import com.texo.model.Producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{

	Producer findByNome(String nome);
	
}
