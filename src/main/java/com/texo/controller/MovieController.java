package com.texo.controller;

import java.util.List;

import com.texo.VH.ProducerWinVH;
import com.texo.model.Movie;
import com.texo.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Movie>> listarTodos(){
		return ResponseEntity.ok(movieService.listarTodos());
	}
	
	@GetMapping("/listarGanhadoresPremio")
	public ResponseEntity<ProducerWinVH> listarGanhadoresPremio(){
		return ResponseEntity.ok(movieService.listarProducerMaisPremio());
	}
	
	
}
