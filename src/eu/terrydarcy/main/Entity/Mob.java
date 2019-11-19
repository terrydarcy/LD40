package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class Mob extends Entity {

	protected Sprite sprite;
	float dx = 0;
	public float dy = 0;
	public float dt = 0.3F;

	public void tick() {

	}

	public void move(float xa, float ya) {
		if (xa > 0 && dx < xa) dx += dt;
		if (xa < 0 && dx > xa) dx -= dt;
		// if (ya > 0 && dy < ya) dy += dt;
		// if (ya < 0 && dy > ya) dy -= dt;
		if (!collision(dx, 0)) x += dx;
		if (!collision(0, ya)) y += ya;

	}

	protected boolean collision(double xa, double ya) {
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 / 1 - 5) / 16;
			double yt = ((y + ya) - c / 2 * 15 / 6 -2) / 16;
			// corners
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) return true;
		}
		return false;
	}

	public void render(Render render) {

	}

}
