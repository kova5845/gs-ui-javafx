package org.graphstream.ui.viewer_fx.test;

import org.graphstream.graph.implementations.MultiGraph;

public class TestStrokeMode {
	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui", "org.graphstream.ui.javafx.util.Display");
		(new TestStrokeMode()).run();
	}

	private void run() {
		MultiGraph graph = new MultiGraph("stroke");

		graph.setAttribute("ui.quality");
	    graph.setAttribute("ui.antialias");
	    graph.setAttribute("ui.stylesheet", styleSheet);
	    graph.display();
	    graph.addNode("A");
	    graph.addNode("B");
	    graph.addNode("C");
	    graph.addNode("D");
	    graph.addEdge("AB", "A", "B");
	    graph.addEdge("BC", "B", "C");
	    graph.addEdge("CA", "C", "A");
	    graph.addEdge("AD", "A", "D");
	    graph.forEach(node -> node.setAttribute("ui.label", node.getId()));
	}
	
	private String styleSheet =
		"node {"+
		"	fill-color: white;"+
		"	fill-mode: plain;"+
		"	stroke-mode: dashes;"+
		"	stroke-width: 1px;"+
		"	stroke-color: red;"+
		"	size: 20px;"+
		"}"+
		"node#A {"+
		"	shape: triangle;"+
		"	stroke-mode: plain;"+
		"}"+
		"node#B {"+
		"	shape: cross;"+
		"	stroke-mode: plain;"+
		"}"+
		"node#C {"+
		"	shape: diamond;"+
		"	stroke-mode: plain;"+
		"}"+
		"node#D {"+
		"	fill-color: gray; "+
		"	stroke-color: blue; "+
		"}"+
		"edge {"+
		//"	fill-mode: none;"+
		"	shape: line;"+
		"	size: 0px;"+
		"	stroke-mode: dashes;"+
		"	stroke-width: 1px;"+
		"	stroke-color: red;"+
		"	fill-color: red;"+
		"}"+
		"edge#BC {"+	
		"	shape: blob; size: 3px; fill-color: #444;"+
		"}"+
		"edge#AD {"+	
		"	stroke-mode: double;"+
		"}";
}