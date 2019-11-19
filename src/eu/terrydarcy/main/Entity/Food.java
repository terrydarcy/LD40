package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.input.Keyboard;

public class Food extends Mob {

	private int speed = 2;
	public float v = 0, gravity = 0.1F;
	protected boolean onGround = false;
	int anim = 0;
	public static final int CALORIES = 1000;

	protected int yOffset;

	public boolean toRemove = false;
	
	public void tick() {
	
	}

	boolean xFlip = false;

	public void render(Render render) {
		
	}

}
