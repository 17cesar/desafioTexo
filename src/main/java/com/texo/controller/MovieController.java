package com.texo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texo.model.Movie;
import com.texo.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Movie>> listarTodos(){
		return ResponseEntity.ok(movieService.listarTodos());
	}
	
	@GetMapping("/movies-winners")
	public ResponseEntity<List<Movie>> listarGanhadoresPremio(){
		return ResponseEntity.ok(movieService.listarGanhadoresPremio());
	}
	
	
}
