package com.ksutarlim.randnamegen.graph;

public class Edge {
	private String source;
	private String target;
	private int count;
	
	public Edge(String source, String target, int count) {
		this.source = source;
		this.target = target;
		this.count = count;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getTarget() {
		return this.target;
	}
	
	public int getCount() {
		return this.count;
	}
}
