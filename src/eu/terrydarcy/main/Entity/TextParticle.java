package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Font;
import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;

public class TextParticle extends Entity {

	protected int lifeTime;
	protected Sprite sprite;
	protected double xa, ya, za;
	protected double xx, yy, zz;
	private boolean xFlip, yFlip;
	private int time = 0;
	private String msg;

	public TextParticle(int x, int y, int lifeTime, String msg) {
		this.msg = msg;
		this.lifeTime = lifeTime + (random.nextInt(20) - 10);
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		xFlip = random.nextBoolean();
		yFlip = random.nextBoolean();

		this.xa = random.nextGaussian() + 0.5;
		this.ya = 2.5;
		this.zz = random.nextGaussian() + 2.0;
	}

	public static boolean lremove = false;

	public void tick() {
		time++;
		if (time >= (Integer.MAX_VALUE - 10)) time = 0;

		if (lremove || time >= lifeTime) {
			toRemove = true;
			lremove = false;
		}
		za -= 0.1;

		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.3;
			ya *= 0.6;
		}

		tickPos(xx + xa, (yy + ya) + zz + za);
	}

	public void tickPos(double x, double y) {
		if (Collision(x, y)) {
			this.xa *= -0.5;
			this.za *= -0.5;
			this.ya *= -0.5;
		}
		this.xx += xa;
		if (!Collision(x, y + 5)) {
			ya += 0.5;
			// this.yy += ya;

			this.yy += ya;
		}
		this.zz += za;
	}

	public boolean Collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x - c % 2 * 16 + 6) / 16);
			double yt = ((y - c / 2 * 16) / 16);
			if (x < 0) x = 0;
			if (y < 0) y = 0;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) {
				return solid = true;
			}
		}
		return solid;
	}

	int col1;

	public void render(Render render) {
		String msgInt = msg.substring(1, msg.length());
		int col = Integer.parseInt(msgInt);
		System.out.println(col);
		if (col > 0) {
			col = 0xD80000;
			col1 = 0x910000;
		}
		if (col <= 0) {
			col = 0x509930;
			col1 = 0x32601F;

		}
		Font.draw(msg, render, (int) xx, (int) yy + (int) zz - 1, col1, true);
		Font.draw(msg, render, (int) xx, (int) yy + (int) zz, col, true);
	}

}
