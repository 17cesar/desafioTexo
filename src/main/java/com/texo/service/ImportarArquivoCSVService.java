package com.texo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.texo.model.Movie;
import com.texo.model.MovieCSV;
import com.texo.model.Producer;
import com.texo.model.Studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class ImportarArquivoCSVService {

	    @Autowired
	    private ProducerService producerService;

	    @Autowired
	    private StudioService studioService;
	    
	    @Autowired
	    private MovieService movieService;

		@PostConstruct
	    @Transactional
	    public void importarDadosArquivo() throws URISyntaxException, IOException {
	        
			ClassPathResource classPathResource = new ClassPathResource("movielist.csv");
	        Reader reader = new InputStreamReader(classPathResource.getInputStream());
	        
	        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	        CSVReader csvReader = new CSVReaderBuilder(reader)
	        		.withSkipLines(1)
	        		.withCSVParser(parser)
	        		.build();
	        
	        List<MovieCSV> listMovies = new CsvToBeanBuilder<MovieCSV>(csvReader)
	        		.withType(MovieCSV.class)
	        		.build()
	        		.stream()
	        		.collect(Collectors.toList());
	       
	        listMovies.stream().forEach(movie -> inserirDadosMovie(movie));
			
	    }
		
		private void inserirDadosMovie(MovieCSV movieCSV) {
			Movie movie = new Movie();
			movie.setTitle(movieCSV.getTitle());
			movie.setYear(movieCSV.getYear());
			movie.setWinner(movieCSV.getWinner().trim().equalsIgnoreCase("yes"));
			movie.getProducers().addAll(inserirDadosProduct(movieCSV.getProducers()));
			movie.getStudios().addAll(inserirDadosStudio(movieCSV.getStudios()));
			movieService.salvar(movie);
		}
		
		private List<Producer> inserirDadosProduct(String nomeProducer){
			List<Producer> list = new ArrayList<Producer>();
			String[] listProducers = nomeProducer.toUpperCase().replace(" AND ", ",").split(",");
			for(String product : listProducers) {
				Producer prod = new Producer();
				prod.setNome(product.trim());
				list.add(producerService.validarSalvar(prod));
			}
			return list;
		}
		
		private List<Studio> inserirDadosStudio(String nomeStudio){
			List<Studio> list = new ArrayList<Studio>();
			nomeStudio.toLowerCase().replace(" AND ", ",");
			String[] listProducers = nomeStudio.split(",");
			for(String studio : listProducers) {
				Studio stud = new Studio();
				stud.setNome(studio);
				list.add(studioService.validarSalvar(stud));
			}
			return list;
		}
	
	
}
