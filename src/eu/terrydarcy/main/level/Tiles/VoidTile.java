package eu.terrydarcy.main.level.Tiles;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid() {
		return true;
	}
	
	public void tick() {
		
	}
	
	public void render(int x, int y, Render render) {
		render.renderSprite(x<<4, y<<4, sprite, false, false);
	}

}
