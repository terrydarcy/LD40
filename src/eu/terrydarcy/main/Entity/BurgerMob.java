package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class BurgerMob extends Food {

	float speed = 1;
	boolean chasing = false;
	boolean stopped = false;
	boolean revealed = false;
	boolean xFlip = false;
	private int dist = 60;
	public static final int CALORIES = 4000;

	public BurgerMob(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.burgerMob0;
		yOffset = -1 + random.nextInt(2);
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
		chasing = false;
		if (level.playerAlive && Math.abs(level.players.get(0).x - x) <= dist & level.players.get(0).y >= y - 10 && level.players.get(0).y < y + 15) {
			dist = 100;
			chasing = true;
			revealed = true;
		} else {
			chasing = false;
		}

		if (!chasing) {
			if (!revealed) stopped = true;
			if (revealed) {
				gravity = 1F;
				xa = speed;
			}
			if (collision(xa, 0)) speed *= -1;
		} else {

			stopped = false;
			if (level.playerAlive && x < level.players.get(0).x) speed = 1;
			if (level.playerAlive && x > level.players.get(0).x) speed = -1;
		}
		if (revealed) xa = speed;

		animate(xa, ya);

		if (!collision(xa, 0)) x += xa;
		if (!collision(0, ya)) y += ya;
	}

	public void animate(float xa, float ya) {
		if ((xa > 0 || xa < 0) && anim < Integer.MAX_VALUE - 10) {
			anim++;
		} else {
			anim = 0;
		}

		if (xa > 0) xFlip = true;
		if (xa < 0) xFlip = false;

		if (!stopped) {
			int frames = 3;
			int rate = frames * 8;
			if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.burgerMob3;
			else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.burgerMob2;
			else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.burgerMob1;
			else sprite = Sprite.burgerMob0;
		} else {
			sprite = Sprite.burger;
		}
	}

	public void render(Render render) {
		render.renderSprite((int) x - 8, (int) y + yOffset, sprite, xFlip, false);
	}
}
