package com.texo.service;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.texo.VH.ProducerWinVH;
import com.texo.model.Movie;
import com.texo.model.Producer;
import com.texo.model.ProducerWin;
import com.texo.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public void salvar(Movie movie) {
		movieRepository.save(movie);
	}
   
    public List<Movie> listarTodos(){
    	return movieRepository.findAll();
    }
	
    public List<Movie> listarGanhadoresPremio(){
    	return movieRepository.findByWinner(Boolean.TRUE);
    }
    
    public ProducerWinVH listarGanhadorIntervalo(){
    	
    	List<Movie> listMovieWins = listarGanhadoresPremio();
    	
    	List<Producer> listProducer = new ArrayList<Producer>();
    	
    	listMovieWins.stream().forEach(movie -> {
    		listProducer.addAll(movie.getProducers());
        });
    	
    	Map<Producer, Long> mapProducer = listProducer.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
    	.entrySet().stream().filter(producer -> producer.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    	
    	ProducerWinVH producerWinVH = new ProducerWinVH();
    	
    	producerWinVH.getMin().addAll(listarProducerWinMin(mapProducer));
    	producerWinVH.getMax().addAll(listarProducerWinMax(mapProducer));
    	
    	return producerWinVH;
    }
    
    private List<ProducerWin> listarProducerWinMin(Map<Producer, Long> mapProducer){
    	List<ProducerWin> listWin = new ArrayList<ProducerWin>();
    	for(Producer producer : mapProducer.keySet()){
    		listWin.add(validarIncluirProducerWin(producer,Boolean.TRUE));
    	}
    	
    	IntSummaryStatistics summary = listWin.stream().collect(Collectors.summarizingInt(ProducerWin::getInterval));
    	
    	return listWin.stream().filter(win -> win.getInterval().equals(summary.getMin())).collect(Collectors.toList());
    }
    
    private List<ProducerWin> listarProducerWinMax(Map<Producer, Long> mapProducer){
    	List<ProducerWin> listWin = new ArrayList<ProducerWin>();
    	for(Producer producer : mapProducer.keySet()){
    		listWin.add(validarIncluirProducerWin(producer,Boolean.FALSE));
    	}
    	
    	IntSummaryStatistics summary = listWin.stream().collect(Collectors.summarizingInt(ProducerWin::getInterval));
    	
    	return listWin.stream().filter(win -> win.getInterval().equals(summary.getMax()) && win.getInterval() > 0).collect(Collectors.toList());
    }
    
    private ProducerWin validarIncluirProducerWin(Producer producer , Boolean min) {
    	ProducerWin producerWin = new ProducerWin();
    	producerWin.setProducer(producer.getNome());
    	List<Movie> listMovie = movieRepository.findByProducersAndWinner(producer , Boolean.TRUE);
    	if(min) {
    		producerWin.setPreviousWin(listMovie.get(0).getYear().toString());
    		producerWin.setFollowingWin(listMovie.get(1).getYear().toString());
    		producerWin.setInterval((listMovie.get(1).getYear() - listMovie.get(0).getYear()));
    		
    	}else {
    		if(listMovie.size() >=4 &&
    		premioConsecutivo(listMovie.get(0).getYear() , listMovie.get(1).getYear()) &&
    		premioConsecutivo(listMovie.get(2).getYear() , listMovie.get(3).getYear())) {
    			producerWin.setPreviousWin(listMovie.get(0).getYear().toString().concat("/").concat(listMovie.get(1).getYear().toString()));
        		producerWin.setFollowingWin(listMovie.get(2).getYear().toString().concat("/").concat(listMovie.get(3).getYear().toString()));
        		producerWin.setInterval((listMovie.get(2).getYear() - listMovie.get(1).getYear()));
    		}	
    	}
    	return producerWin;
    }
    
    private Boolean premioConsecutivo(Integer anoInicial , Integer anoFinal) {
    	return ((anoFinal - anoInicial) == 1);
    }
	
}
