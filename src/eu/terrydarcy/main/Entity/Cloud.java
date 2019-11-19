package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.Game;
import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class Cloud extends Entity {

	boolean xFlip = false;
	int time = 0;
	int randMove = 0;

	public Cloud(int x, int y) {
		this.x = x;
		this.y = y;
		xFlip = random.nextBoolean();
		randMove = 25 + random.nextInt(50);
	}

	public void tick() {
		time++;
		if (time % randMove == 0) x++;
		if (x > Game.W * Game.SCALE + 100) {
			y = 0 + random.nextInt(10 * 16);
			x = -100;
		}
	}

	public void render(Render render) {
		render.renderSprite((int) x, (int) y, Sprite.cloud, false, false);
	}

}
