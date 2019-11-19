package eu.terrydarcy.main.level.Tiles;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.level.Level;

public class StoneTopTile extends Tile {

	public StoneTopTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return true;
	}

	public void tick() {

	}

	public void render(int x, int y, Render render, Level level) {
		render.renderSprite(x << 4, y << 4, sprite, false, false);
	}

}
