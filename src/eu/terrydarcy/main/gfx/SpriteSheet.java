package eu.terrydarcy.main.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

	public int[] pixels;
	public int SIZE;

	public static SpriteSheet sheet = new SpriteSheet(192, "/sheets/sheet.png");
	public static SpriteSheet icons = new SpriteSheet(256, "/sheets/icons.png");
	public static SpriteSheet title = new SpriteSheet(144, "/sheets/title.png");
	public static SpriteSheet playerSheet = new SpriteSheet(384, "/sheets/playerSheet.png");

	public SpriteSheet(int size, String path) {
		this.SIZE = size;
		this.pixels = new int[SIZE * SIZE];
		load(path);
	}

	private void load(String path) {
		try {
			BufferedImage sheet = ImageIO.read(BufferedImage.class.getResource(path));
			int w = sheet.getWidth();
			int h = sheet.getHeight();
			sheet.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
