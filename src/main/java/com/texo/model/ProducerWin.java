package com.texo.model;

public class ProducerWin {

	private String producer;
	private Integer interval;
	private String previousWin;
	private String followingWin;
	
	public String getProducer() {
		if(producer == null) {
			producer = "";
		}
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Integer getInterval() {
		if(interval == null) {
			interval = 0;
		}
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getPreviousWin() {
		if(previousWin == null) {
			previousWin = "";
		}
		return previousWin;
	}
	public void setPreviousWin(String previousWin) {
		this.previousWin = previousWin;
	}
	public String getFollowingWin() {
		if(followingWin == null) {
			followingWin = "";
		}
		return followingWin;
	}
	public void setFollowingWin(String followingWin) {
		this.followingWin = followingWin;
	}
	
	
	
	
}
