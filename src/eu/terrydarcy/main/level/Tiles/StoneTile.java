package eu.terrydarcy.main.level.Tiles;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.level.Level;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return true;
	}

	public void tick() {

	}

	public void render(int x, int y, Render render, Level level) {
		// System.out.println(level.getTile(x, y - 1));
		boolean xFlip = false, yFlip = false;
		boolean up = false, left = false, right = false, innerLeft = false, innerRight= false, grassLeft= false, grassRight= false,top = false;
		
		
		if (level.getTile(x-1, y) == Tile.grass) grassLeft = true;
		if (level.getTile(x+1, y) == Tile.grass) grassRight = true;
		if (level.getTile(x, y-1) == Tile.air) top = true;

		
		if (level.getTile(x, y - 1) == Tile.dirt) up = true;
		if (level.getTile(x - 1, y) == Tile.dirt) left = true;
		if (level.getTile(x + 1, y) == Tile.dirt) right = true;
		if (level.getTile(x + 1, y-1) == Tile.dirt && level.getTile(x, y-1) == Tile.stone&& level.getTile(x+1, y) == Tile.stone) innerRight = true;
		if (level.getTile(x - 1, y-1) == Tile.dirt && level.getTile(x, y-1) == Tile.stone&& level.getTile(x-1, y) == Tile.stone) innerLeft = true;
		else sprite = Sprite.stone;

		if (up) {
			sprite = Sprite.stoneDirtTransitionUp;
		}
		if (left) {
			sprite = Sprite.stoneDirtTransitionRight;
			xFlip = true;

		}
		if (right) {
			sprite = Sprite.stoneDirtTransitionRight;
			xFlip = false;
		}
		
		if(up&&left) {
			sprite = Sprite.stoneDirtTransitionCorner;	
		}
		if(up&&right) {
			sprite = Sprite.stoneDirtTransitionCorner;
		}
		
		
		if(innerRight) {
			sprite = Sprite.stoneDirtTransitionCornerIn;
			yFlip = true;
		}
		if(innerLeft) {
			sprite = Sprite.stoneDirtTransitionCornerIn;
			xFlip = yFlip=true;
		}
		
		if (grassLeft) {
			sprite = Sprite.stoneDirtTransitionCornerIn;
			xFlip = true;

		}
		if (grassRight) {
			sprite = Sprite.stoneDirtTransitionCornerIn;
			xFlip = false;
		}
		if(top) {
			sprite = Sprite.stoneTop;
		}
		
		

		render.renderSprite(x << 4, y << 4, sprite, xFlip, yFlip);
	}

}
