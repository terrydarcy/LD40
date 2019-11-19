package eu.terrydarcy.main.gfx;

public class Render {

	public int w, h, xOffset, yOffset;
	public int[] pixels;

	public Render(int w, int h, int[] pixels) {
		this.w = w;
		this.h = h;
		this.pixels = pixels;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderRect(int x0, int y0, int x1, int y1, int col) {
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				pixels[x + y * w] = col;
			}
		}
	}

	public void render(int xp, int yp, int tile, int colors, boolean xFlip, boolean yFlip, boolean following) {
		if (following) {
			xp -= xOffset;
			yp -= yOffset;
		}

		int xTOff = tile % 32;
		int yTOff = tile / 32;
		int tileOffs = xTOff * 8 + yTOff * 8 * SpriteSheet.icons.SIZE;

		for (int y = 0; y < 8; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) ys = 7 - y;
			for (int x = 0; x < 8; x++) {
				int xa = x + xp;
				if (xa <= -8 || xa >= w || ya <= 0 || ya >= h) break;
				if(xa<0)xa= 0;
				int xs = x;
				if (xFlip) xs = 7 - x;
				int col = ((SpriteSheet.icons.pixels[xs + ys * SpriteSheet.icons.SIZE + tileOffs]));
				if (col != 0xffff00ff) {
					if (col == 0xffffffff) col = colors;
					pixels[xa + ya * w] = col;

				}
			}
		}
	}

	public void renderSprite(int xa, int ya, Sprite sprite, boolean xflip, boolean yflip) {
		xa -= xOffset;
		ya -= yOffset;
		for (int y = 0; y < sprite.size; y++) {
			int yp = y + ya;
			int ys = y;
			if (yflip) ys = sprite.size - 1 - y;
			for (int x = 0; x < sprite.size; x++) {
				int xp = x + xa;
				int xs = x;
				if (xp < -sprite.size || xp >= w || yp < 0 || yp >= h) break;
				if (xp < 0) xp = 0;
				if (xflip) xs = sprite.size - 1 - x;
				int col = sprite.pixels[xs + ys * sprite.size];
				if (col != 0xffff00ff) pixels[xp + yp * w] = col;
			}
		}
	}
	public void renderSpriteToBack(int xa, int ya, Sprite sprite, boolean xflip, boolean yflip) {
		for (int y = 0; y < sprite.size; y++) {
			int yp = y + ya;
			int ys = y;
			if (yflip) ys = sprite.size - 1 - y;
			for (int x = 0; x < sprite.size; x++) {
				int xp = x + xa;
				int xs = x;
				if (xp < -sprite.size || xp >= w || yp < 0 || yp >= h) break;
				if (xp < 0) xp = 0;
				if (xflip) xs = sprite.size - 1 - x;
				int col = sprite.pixels[xs + ys * sprite.size];
				if (col != 0xffff00ff) pixels[xp + yp * w] = col;
			}
		}
	}

	public void renderSprite(int xa, int ya, Sprite sprite) {
		xa -= xOffset;
		ya -= yOffset;
		for (int y = 0; y < sprite.size; y++) {
			int yp = y + ya;
			for (int x = 0; x < sprite.size; x++) {
				int xp = x + xa;
				if (xp < -sprite.size || xp >= w || yp < 0 || yp >= h) break;
				if (xp < 0) xp = 0;
				pixels[xp + yp * w] = sprite.col;
			}
		}
	}

	public void setOffsets(int xScroll, int yScroll) {
		this.xOffset = xScroll;
		this.yOffset = yScroll;
	}

}
