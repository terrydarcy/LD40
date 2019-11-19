package eu.terrydarcy.main.level.Tiles;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.level.Level;

public class Tile {
	
	protected Sprite sprite;
	
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile dirt = new DirtTile(Sprite.dirt);
	public static Tile air = new AirTile(Sprite.air);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile stoneTop = new StoneTopTile(Sprite.stoneTop);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile dirtBackTile = new DirtBackTile(Sprite.dirtBack);

	public Tile(Sprite sprite) {
		this.sprite=sprite;
	}
	
	public boolean solid() {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(int x, int y, Render render, Level level) {
		
	}
	
}
