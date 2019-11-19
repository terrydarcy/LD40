package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class ChickenSpikes extends Food {

	public static final int CALORIES = 8000;
	private boolean xFlip = false;

	public ChickenSpikes(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.chickenSpikes;
		yOffset = 1 + random.nextInt(4);
		xFlip = random.nextBoolean();

	}

	public void tick() {
		float xa = 0, ya = 0;

		ya += v * dt;
		v += gravity * dt;

		if (v * dt > 0 && collision(0, v * dt)) {
			onGround = true;
			v = 0;

		}

		if (collision(0, v * dt)) {
			v = 0;

		}

		move(xa, ya);
	}

	public void render(Render render) {
		render.renderSprite((int) x - 8, (int) y + yOffset, sprite, xFlip, false);
	}
}
