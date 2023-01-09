package com.texo.VH;

import java.util.ArrayList;
import java.util.List;

import com.texo.model.ProducerWin;

public class ProducerWinVH {
	
	private List<ProducerWin> min;
	private List<ProducerWin> max;
	
	public List<ProducerWin> getMin() {
		if(min == null) {
			min = new ArrayList<ProducerWin>();
		}
		return min;
	}
	public void setMin(List<ProducerWin> min) {
		this.min = min;
	}
	public List<ProducerWin> getMax() {
		if(max == null) {
			max = new ArrayList<ProducerWin>();
		}
		return max;
	}
	public void setMax(List<ProducerWin> max) {
		this.max = max;
	}
	
	

}
