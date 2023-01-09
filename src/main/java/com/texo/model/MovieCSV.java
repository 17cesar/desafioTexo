package com.texo.model;

import com.opencsv.bean.CsvBindByPosition;

public class MovieCSV {

	@CsvBindByPosition(position = 0)
    private Integer year;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String studios;

    @CsvBindByPosition(position = 3)
    private String producers;

    @CsvBindByPosition(position = 4)
    private String winner;

	
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

	public String getStudios() {
		if (studios == null) {
			studios = "";
		}
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		if (producers == null) {
			producers = "";
		}
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		if (winner == null) {
			winner = "";
		}
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}
