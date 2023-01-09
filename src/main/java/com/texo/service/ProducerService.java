package com.texo.service;

import java.util.List;

import com.texo.model.Producer;
import com.texo.repository.ProducerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
    private ProducerRepository producerRepository;
	
	public Producer validarSalvar(Producer producer) {
		
		Producer producerTemp = producerRepository.findByNome(producer.getNome());
		
		if(producerTemp != null) {
			return producerTemp;
		}
		
		return producerRepository.save(producer);
	}
	
	public List<Producer> listarTodos(){
		return producerRepository.findAll();
	}
	
	
}
