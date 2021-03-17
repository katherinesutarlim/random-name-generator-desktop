package com.ksutarlim.randnamegen.generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ksutarlim.randnamegen.graph.Graph;
import com.ksutarlim.randnamegen.graph.GraphData;
import com.ksutarlim.randnamegen.graph.Node;
import com.ksutarlim.randnamegen.graph.Probability;
import com.ksutarlim.randnamegen.graph.Type;

public class Generator {
//	public List<Node> nodeList = new ArrayList<Node>();
	private Graph graph;
	
	public void loadData() {
		
		Gson gson = new Gson();
		try {
			JsonReader reader = new JsonReader(new FileReader("src/main/resources/patterns.json"));
			GraphData graphData = gson.fromJson(reader, GraphData.class);
//			System.out.println(graphData.getNodes()[0].getId());
			this.graph = new Graph(graphData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String generate() {
		Node currentNode = this.graph.getRandomNode();
		while (currentNode.getProbability().getStart() == 0) {
			currentNode = this.graph.getRandomNode();
		}
		String name = currentNode.getId().substring(0, 1).toUpperCase()
				+ currentNode.getId().substring(1);
		
		Random random = new Random();
		Type currentType = Type.START;
		while (currentType != Type.END) {
			List<Node> next = currentNode.getNext();
			if (next.size() == 0) {
				break;
			}
			List<Type> nextTypes = new ArrayList<Type>();
			switch(currentType) {
				case START:
					nextTypes.add(Type.BETWEEN);
					break;
				case BETWEEN:
					nextTypes.add(Type.START);
					nextTypes.add(Type.END);
					break;
			}
			int allCountTotal = 0;
			for (Node node : next) {
				for(Type type : currentType.getDeclaringClass().getEnumConstants()) {
					allCountTotal += node.getProbability().get(type);
				}
			}
			if (allCountTotal == 0) {
				break;
			}
			int chooseType = random.nextInt(allCountTotal);
			int countSoFar = 0;
			Node nextNode = null;
			for (Node node : next) {
				for(Type type : currentType.getDeclaringClass().getEnumConstants()) {
					if (chooseType >= countSoFar
							&& chooseType < countSoFar + node.getProbability().get(type)) {
						nextNode = node;
						currentType = type;
						break;
					} else {
						countSoFar += node.getProbability().get(type);
					}
				}
				if (nextNode != null) {
					break;
				}
			}
			currentNode = nextNode;
			name += currentNode.getId();
			if (currentType != Type.END && name.length() > 10) {
				break;
			};
		}
		return name;
	}
}
