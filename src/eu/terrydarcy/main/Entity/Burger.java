package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class Burger extends Food {
	public static final int CALORIES = 900;

	public Burger(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.burger;
		yOffset = 1 + random.nextInt(4);
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
		
/*		for (int yy = 0; yy < level.h; yy++) {
			if (level.getTile((int) x / 16, (int) yy+1 / 16).solid()) {
				dy = yy*16;
				return;
			}
		}
*/
		move(xa, ya);
	}

	int yD =0;
	public void render(Render render) {
		render.renderSprite((int) x - 8, (int) y + yOffset, sprite, false, false);
		render.renderSprite((int) x - 8, (int) y + yOffset, sprite, false, false);
	}
}
