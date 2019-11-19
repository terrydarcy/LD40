package eu.terrydarcy.main.level.Tiles;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.level.Level;

public class DirtBackTile extends Tile {

	public DirtBackTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid() {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(int x, int y, Render render, Level level) {
		if (level.getTile(x, y+1) == Tile.grass) {
			sprite = Sprite.air;
		}else {
			sprite= Sprite.dirtBack;
		}
		render.renderSprite(x<<4, y<<4, sprite, false, false);
	}

}
