package OctahedronBase;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OctahedronKeyListener implements KeyListener {
	
	private char managerAction = '.';
	
	public OctahedronKeyListener () {
	
	}
	
	/*
	 * This method returns a code to be acted upon by the controlling OctahedronManager object and resets the value to '.'
	 * '.' is the default code and should not cause any actions
	 */
	public char getManagerAction () {
		
		char temp = managerAction;
		managerAction = '.';
		return temp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		// Any button inputs will get relayed to the controlling OctahedronManager object via the managerAction variable
		managerAction = e.getKeyChar();
		System.out.println(managerAction);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}
}
