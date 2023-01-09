package com.texo.service;

import java.util.List;

import com.texo.model.Studio;
import com.texo.repository.StudioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

	@Autowired
    private StudioRepository studioRepository;
	
	public Studio validarSalvar(Studio studio) {
		
		Studio studioTemp = studioRepository.findByNome(studio.getNome());
		
		if(studioTemp != null) {
			return studioTemp;
		}
		
		return studioRepository.save(studio);
	}
	
	public List<Studio> listarTodos(){
		return studioRepository.findAll();
	}
	
	
}
