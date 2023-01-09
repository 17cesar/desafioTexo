package com.texo.repository;

import com.texo.model.Studio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>{
	
	Studio findByNome(String nome);

}
