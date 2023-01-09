package com.texo.controller;

import java.util.List;

import com.texo.model.Producer;
import com.texo.service.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {
	
	@Autowired
    private ProducerService producerService;
	
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Producer>> listarTodos(){
		return ResponseEntity.ok(producerService.listarTodos());
	}

}
