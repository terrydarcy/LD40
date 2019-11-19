package eu.terrydarcy.main.Entity;

import java.util.Random;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.level.Level;

public class Entity {
	
	public float x,y;
	protected Level level;
	protected Random random = new Random();
	public boolean toRemove = false;
	
	public void init(Level level) {
		this.level = level;
	}
	
	public void tick() {

	}
	
	public void render(Render render) {

	}

}
