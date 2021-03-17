package com.ksutarlim.randnamegen.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Node {
	private String id;
	private Probability probability;
	private List<Node> next = new ArrayList<Node>();
	
	public Node(String id, Probability probability) {
		this.id = id;
		this.probability = probability;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Probability getProbability() {
		return this.probability;
	}
	
	public void addNext(Node node) {
		if (this.next == null) {
			this.next = new ArrayList<Node>();
		}
		this.next.add(node);
	}
	
	public List<Node> getNext() {
		return this.next;
	}
	
//	public Node toNext() {
//		int sum = 0;
//		for (Map.Entry<String, Edge> entry: this.next.entrySet()) {
//			sum += entry.getValue().getCount();
//		}
//		Random rng = new Random();
//		int randomNum = rng.nextInt(sum);
//		int latestSum = 0;
//		Node next = null;
//		for (Map.Entry<String, Edge> entry: this.next.entrySet()) {
//			latestSum += entry.getValue().getCount();
//			if (latestSum > randomNum) {
//				next = entry.getValue().getTarget();
//				break;
//			}
//		}
//		return next;
//	}
}
