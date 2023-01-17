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
    		listWin.addAll(validarIncluirProducerWin(producer,Boolean.TRUE));
    	}
    	
    	IntSummaryStatistics summary = listWin.stream().collect(Collectors.summarizingInt(ProducerWin::getInterval));
    	
    	return listWin.stream().filter(win -> win.getInterval().equals(summary.getMin())).collect(Collectors.toList());
    }
    
    private List<ProducerWin> listarProducerWinMax(Map<Producer, Long> mapProducer){
    	List<ProducerWin> listWin = new ArrayList<ProducerWin>();
    	for(Producer producer : mapProducer.keySet()){
    		listWin.addAll(validarIncluirProducerWin(producer,Boolean.FALSE));
    	}
    	
    	IntSummaryStatistics summary = listWin.stream().collect(Collectors.summarizingInt(ProducerWin::getInterval));
    	
    	return listWin.stream().filter(win -> win.getInterval().equals(summary.getMax()) && win.getInterval() > 0).collect(Collectors.toList());
    }
    
    private Integer qtd;
    
    private List<ProducerWin> validarIncluirProducerWin(Producer producer , Boolean min) {
    	this.qtd = 0;
    	List<ProducerWin> wins = new ArrayList<ProducerWin>();
    	List<Movie> listMovie = movieRepository.findByProducersAndWinner(producer , Boolean.TRUE);
    	listMovie.stream().filter(a -> qtd < listMovie.size() -1 ).forEach(mov -> {
    		ProducerWin producerWin = new ProducerWin();
    		producerWin.setProducer(producer.getNome());
    		producerWin.setPreviousWin(mov.getYear().toString());
    		producerWin.setFollowingWin(listMovie.get(qtd + 1).getYear().toString());
    		producerWin.setInterval((Integer.parseInt(producerWin.getFollowingWin()) - Integer.parseInt(producerWin.getPreviousWin())));
    		this.qtd++;
    		wins.add(producerWin);
    	});
    	return wins;
    }
	
}
