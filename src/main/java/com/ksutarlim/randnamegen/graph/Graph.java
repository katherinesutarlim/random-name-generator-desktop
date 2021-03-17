package com.ksutarlim.randnamegen.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Graph {
	private Map<String, Node> nodesMap;
	private Node[] nodes;
	
	public Graph(GraphData graphData) {
		this.nodesMap = new HashMap<String, Node>();
		this.nodes = graphData.getNodes();
		for (Node node : graphData.getNodes()) {
			nodesMap.put(node.getId(), node);
		}
		for (Edge edge : graphData.getEdges()) {
			Node source = this.nodesMap.get(edge.getSource());
			Node target = this.nodesMap.get(edge.getTarget());
			source.addNext(target);
		}
	}
	
	public Node getNode(String id) {
		return this.nodesMap.get(id);
	}
	
	public Node getRandomNode() {
		Random random = new Random();
		return this.nodes[random.nextInt(this.nodes.length)];
	}
}
