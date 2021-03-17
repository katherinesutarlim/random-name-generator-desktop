package com.ksutarlim.randnamegen.graph;

import java.util.HashMap;
import java.util.Map;

public class GraphData {
	private Node[] nodes;
	private Edge[] edges;
	
	public GraphData(Node[] nodes, Edge[] edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public Node[] getNodes() {
		return this.nodes;
	}
	
	public Edge[] getEdges() {
		return this.edges;
	}
}
