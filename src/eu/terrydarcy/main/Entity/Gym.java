package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class Gym extends Entity {

	public Gym(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		if (level.playerAlive && Math.abs(level.players.get(0).x - (x + 20) - 10) < 40 && level.playerAlive && Math.abs(level.players.get(0).y - (y - 20) - 10) < 40) {
			level.switchLevel(level.lvl + 1);
			System.out.println("Changing Level");
		}
	}

	public void render(Render render) {
		render.renderSprite((int) x, (int) y - 45, Sprite.gym, false, false);
	}

}
