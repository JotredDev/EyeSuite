package OctahedronBase;

import main.EyeFrame;

public class OctahedronManager implements Runnable {
	
	private final OctahedronPanel panel;
	private final OctahedronKeyListener keyListener;
	
	// This array holds the 3D positions of the vertices of the octahedron
	private double[][] vertexPositions;
	
	// These booleans decide whether the octahedron gets rotated
	private boolean xRotation;
	private boolean yRotation;
	private boolean zRotation;
	private boolean pauseRotation;
	
	// These doubles decide how many radians the octahedron gets rotated every frame
	private double xRadians;
	private double yRadians;
	private double zRadians;
	
	
	public OctahedronManager (EyeFrame eyeFrame) {
	
		this.panel = new OctahedronPanel();
		this.keyListener = new OctahedronKeyListener();
		
		this.vertexPositions = new double[][] {
				new double[] {0, 0, -1},
				new double[] {0, -1, 0},
				new double[] {1, 0, 0},
				new double[] {0, 1, 0},
				new double[] {-1, 0, 0},
				new double[] {0, 0, 1}
		};
		
		this.panel.setVertexPositions(vertexPositions);
		
		this.xRotation = false;
		this.yRotation = false;
		this.zRotation = true;
		this.pauseRotation = false;
		
		this.xRadians = - Math.PI / (120);
		this.yRadians = - Math.PI / (120);
		this.zRadians = - Math.PI / (120);
		
		eyeFrame.add(panel);
		eyeFrame.addKeyListener(keyListener);
		eyeFrame.pack();
		eyeFrame.setVisible(true);
		eyeFrame.repaint();
	}
	
	/*
	 * This method is intended to be run if you want more than a static picture of an octahedron
	 * As it is supposed to run indefinitely, it is designed to run in a separate thread from main
	 */
	@Override
	public void run() {
		
		long lastFrame = System.currentTimeMillis();
		char actionCode;
		
		while(true) {
			
			// This will hopefully give around ~60 fps
			if (System.currentTimeMillis() - lastFrame > 15) {
				
				lastFrame = System.currentTimeMillis();
				
				
				// Check if the keyListener has gotten any new inputs
				actionCode = this.keyListener.getManagerAction();
				
				// Execute whatever the current actionCode dictates
				switch (actionCode) {
					case 'v' -> panel.invertDrawVertices();
					case 'e' -> panel.invertDrawEdges();
					case 'i' -> panel.invertDrawInnerEdges();
					case 'h' -> panel.invertHighlightSpecificEdges();
					case 'x' -> xRotation = !xRotation;
					case 'y' -> yRotation = !yRotation;
					case 'z' -> zRotation = !zRotation;
					case ' ' -> pauseRotation = !pauseRotation;
				}
				
				// If the rotation is not paused, rotate along all axis currently enabled
				if (!pauseRotation) {
					
					if (xRotation) rotateX(vertexPositions, xRadians);
					if (yRotation) rotateY(vertexPositions, yRadians);
					if (zRotation) rotateZ(vertexPositions, zRadians);
				}
				
				// At the end of the frame, repaint the panel
				panel.repaint();
			}
		}
	}
	
	// Simple in-place rotation along the X-axis
	public void rotateX (double[][] positions, double radians) {
		
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		
		for (int i = 0; i < 6; i++) {
			
			double y = cos * positions[i][1] - sin * positions[i][2];
			double z = sin * positions[i][1] + cos * positions[i][2];
			
			positions[i][1] = y;
			positions[i][2] = z;
		}
	}
	
	// Simple in-place rotation along the Y-axis
	public void rotateY (double[][] positions, double radians) {
		
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		
		for (int i = 0; i < 6; i++) {
			
			double x = cos * positions[i][0] - sin * positions[i][2];
			double z = sin * positions[i][0] + cos * positions[i][2];
			
			positions[i][0] = x;
			positions[i][2] = z;
		}
	}
	
	// Simple in-place rotation along the Z-axis
	public void rotateZ (double[][] positions, double radians) {
		
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		
		for (int i = 0; i < 6; i++) {
			
			double x = cos * positions[i][0] - sin * positions[i][1];
			double y = sin * positions[i][0] + cos * positions[i][1];
			
			positions[i][0] = x;
			positions[i][1] = y;
		}
	}
	
	
	
}
