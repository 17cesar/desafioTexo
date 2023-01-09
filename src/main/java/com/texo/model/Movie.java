package com.texo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "movieYear")
	private Integer year;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "winner")
	private Boolean winner;
	
	@ManyToMany
    @JoinTable(name="movie_has_producer", joinColumns=
    {@JoinColumn(name="movieId")}, inverseJoinColumns=
    {@JoinColumn(name="producerId")})	
	private List<Producer> producers;
	
	
	@ManyToMany
    @JoinTable(name="movie_has_studio", joinColumns=
    {@JoinColumn(name="movieId")}, inverseJoinColumns=
    {@JoinColumn(name="studioId")})	
	private List<Studio> studios;

	public Long getId() {
		if (id == null) {
			id = 0L;
		}
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		if (year == null) {
			year = 0;
		}
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		if (title == null) {
			title = "";
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getWinner() {
		if (winner == null) {
			winner = Boolean.FALSE;
		}
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public List<Producer> getProducers() {
		if (producers == null) {
			producers = new ArrayList<Producer>();
		}
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public List<Studio> getStudios() {
		if (studios == null) {
			studios = new ArrayList<Studio>();
		}
		return studios;
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

}
