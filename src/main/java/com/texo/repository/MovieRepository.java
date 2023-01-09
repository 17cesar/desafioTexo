package com.texo.repository;

import java.util.List;

import com.texo.model.Movie;
import com.texo.model.Producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	List<Movie> findByWinner(Boolean winner);
	
	List<Movie> findByProducersAndWinner(Producer producer , Boolean winner);

}
