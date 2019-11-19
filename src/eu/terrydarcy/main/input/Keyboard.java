package eu.terrydarcy.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public boolean[] keys = new boolean[2000];
	public boolean up, down, left, right, jump, reset, shift, cheat, music;

	public void tick() {
		jump = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE] ;
		down = keys[KeyEvent.VK_S]|| keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A]|| keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D]|| keys[KeyEvent.VK_RIGHT];
		reset = keys[KeyEvent.VK_R];
		shift = keys[KeyEvent.VK_SHIFT]|| keys[KeyEvent.VK_SPACE];
		cheat = keys[KeyEvent.VK_9];
		music = keys[KeyEvent.VK_M];
	}

	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
