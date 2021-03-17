package com.ksutarlim.randnamegen.graph;

public class Probability {
	private int start;
	private int between;
	private int end;
	
	public Probability(int start, int between, int end) {
		this.start = start;
		this.between = between;
		this.end = end;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getBetween() {
		return this.between;
	}
	
	public int getEnd() {
		return this.end;
	}
	
	public int get(Type type) {
		if (type == Type.START) {
			return this.start;
		} else if (type == Type.BETWEEN) {
			return this.between;
		} else {
			return this.end;
		}
	}
	
	public void set(Type type, int value) {
		if (type == Type.START) {
			this.start += value;
		} else if (type == Type.BETWEEN) {
			this.between += value;
		} else {
			this.end += value;
		}
	}
}
