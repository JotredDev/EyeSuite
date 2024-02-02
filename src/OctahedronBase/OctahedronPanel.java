package OctahedronBase;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class OctahedronPanel extends JPanel {
	
	// These attributes determine the exact position of the center of the octahedron
	private final int xOffset = 200;
	private final int yOffset = 200;
	private final double unitsPerPixel = 100;
	
	// These booleans decide which parts of the octahedron get drawn
	private boolean drawVertices;
	private boolean drawEdges;
	private boolean drawInnerEdges;
	
	// This is the 2D double array holding the 3D positions of the vertices of the octahedron
	private double[][] vertexPositions;
	
	// If you only want to draw specific edges, simply denote the vertices between which you want to draw edges in this array
	private int[][] specificEdges;
	
	// If you only want to highlight the previously specified edges, set this boolean to true
	private boolean highlightSpecificEdges;
	
	private final double cos = 0.447213595499957;
	private final double sin = 0.894427190999915;
	
	// These are the colors used when drawing background / vertices / edges / highlighted edges
	private final Color backgroundColor = Color.DARK_GRAY;
	private final Color vertexColor = Color.BLACK;
	private final Color edgeColor = Color.RED;
	private final Color specifiedEdgeColor = Color.GREEN;
	
	public OctahedronPanel () {
		
		super();
		
		this.drawVertices = true;
		this.drawEdges = true;
		this.drawInnerEdges = true;
		
		this.highlightSpecificEdges = false;
		
		this.setPreferredSize(new Dimension(xOffset << 1, yOffset << 1));
	}
	
	public OctahedronPanel (boolean drawVertices, boolean drawEdges, boolean drawInnerEdges) {
		
		super();
		
		this.drawVertices = drawVertices;
		this.drawEdges = drawEdges;
		this.drawInnerEdges = drawInnerEdges;
		
		this.highlightSpecificEdges = false;
		
		this.setPreferredSize(new Dimension(xOffset << 1, yOffset << 1));
	}
	
	public OctahedronPanel (boolean drawVertices, boolean drawEdges, boolean drawInnerEdges, boolean highlightSpecificEdges) {
		
		super();
		
		this.drawVertices = drawVertices;
		this.drawEdges = drawEdges;
		this.drawInnerEdges = drawInnerEdges;
		
		this.highlightSpecificEdges = highlightSpecificEdges;
		
		this.setPreferredSize(new Dimension(xOffset << 1, yOffset << 1));
	}
	
	public void setDrawVertices (boolean drawVertices) {
		this.drawVertices = drawVertices;
	}
	public void setDrawEdges (boolean drawEdges) {
		this.drawEdges = drawEdges;
	}
	public void setDrawInnerEdges (boolean drawInnerEdges) {
		this.drawInnerEdges = drawInnerEdges;
	}
	
	public void invertDrawVertices () {
		this.drawVertices = ! this.drawVertices;
	}
	public void invertDrawEdges () {
		this.drawEdges = ! this.drawEdges;
	}
	public void invertDrawInnerEdges () {
		this.drawInnerEdges = ! this.drawInnerEdges;
	}
	
	public void setVertexPositions (double[][] vertexPositions) {
		// Keep in mind this will only set the reference, so you can change the values without having to hand over a new array every time
		this.vertexPositions = vertexPositions;
	}
	
	public void setSpecificEdges (int[][] specificEdges) {
		this.specificEdges = specificEdges;
	}
	public void setHighlightSpecificEdges (boolean highlightSpecificEdges) {
		this.highlightSpecificEdges = highlightSpecificEdges;
	}
	
	public void invertHighlightSpecificEdges () {
		this.highlightSpecificEdges = ! this.highlightSpecificEdges;
	}
	
	/*
	 * This method will paint everything related to the octahedron.
	 *
	 * The vertices have the following ids:
	 * Top: 0
	 * Bottom: 5
	 * Center: 6
	 * And looking from the top:
	 * Up: 1
	 * Right: 2
	 * Down: 3
	 * Left: 4
	 *
	 * Every specifiedEdge should be of the form {a, b}
	 */
	public void paintComponent (Graphics g) {
		
		// As the input is a double in three dimensions, we first calculate the integer pixel positions in 2D
		int[] xPositions = new int[7];
		int[] yPositions = new int[7];
		
		xPositions[6] = xOffset;
		yPositions[6] = yOffset;
		
		for (int i = 0; i < 6; i++) {
		
			// Just simple cabinet projection to turn 3D into 2D
			xPositions[i] = xOffset + (int) ((vertexPositions[i][0] - 0.5 * vertexPositions[i][1] * cos) * unitsPerPixel);
			yPositions[i] = yOffset + (int) ((vertexPositions[i][2] + 0.5 * vertexPositions[i][1] * sin) * unitsPerPixel);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		// Fill the background with your color of choice
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, xOffset << 1, yOffset << 1);
		
		
		// If drawVertices == true, draw hollow 3x3 Rectangles around the vertex positions and around the center of the octahedron
		if (drawVertices) {
			
			g2d.setColor(vertexColor);
		
			for (int i = 0; i < 7; i++) {
				
				g2d.drawRect(xPositions[i] - 1, yPositions[i] - 1, 2, 2);
			}
		}
		
		
		// If drawEdges == true, draw all outer edges of the octahedron
		if (drawEdges) {
			
			g2d.setColor(edgeColor);
			
			g2d.drawLine(xPositions[0], yPositions[0], xPositions[1], yPositions[1]);
			g2d.drawLine(xPositions[0], yPositions[0], xPositions[2], yPositions[2]);
			g2d.drawLine(xPositions[0], yPositions[0], xPositions[3], yPositions[3]);
			g2d.drawLine(xPositions[0], yPositions[0], xPositions[4], yPositions[4]);
			
			g2d.drawLine(xPositions[5], yPositions[5], xPositions[1], yPositions[1]);
			g2d.drawLine(xPositions[5], yPositions[5], xPositions[2], yPositions[2]);
			g2d.drawLine(xPositions[5], yPositions[5], xPositions[3], yPositions[3]);
			g2d.drawLine(xPositions[5], yPositions[5], xPositions[4], yPositions[4]);
			
			g2d.drawLine(xPositions[1], yPositions[1], xPositions[2], yPositions[2]);
			g2d.drawLine(xPositions[1], yPositions[1], xPositions[4], yPositions[4]);
			g2d.drawLine(xPositions[3], yPositions[3], xPositions[2], yPositions[2]);
			g2d.drawLine(xPositions[3], yPositions[3], xPositions[4], yPositions[4]);
			
			
			// If drawInnerEdges == true, draw the "edges" going through the middle of the octahedron
			if (drawInnerEdges) {
				
				g2d.drawLine(xPositions[0], yPositions[0], xPositions[5], yPositions[5]);
				g2d.drawLine(xPositions[1], yPositions[1], xPositions[3], yPositions[3]);
				g2d.drawLine(xPositions[2], yPositions[2], xPositions[4], yPositions[4]);
			}
		}
		
		// If there are specified edges given, draw them with the specified edge color
		// This color should be different from the normal edge color to differentiate them from the other edges, if they are drawn
		if (specificEdges != null && highlightSpecificEdges) {
			
			g2d.setColor(specifiedEdgeColor);
			
			for (int[] specificEdge : specificEdges) {
				
				g2d.drawLine(xPositions[specificEdge[0]], yPositions[specificEdge[0]], xPositions[specificEdge[1]], yPositions[specificEdge[1]]);
			}
		}
	}
}
