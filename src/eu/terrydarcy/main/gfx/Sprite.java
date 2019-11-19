package eu.terrydarcy.main.gfx;

public class Sprite {

	private SpriteSheet sheet;
	public int x, y, size;
	public int[] pixels;
	public int col;

	public static Sprite test = new Sprite(0, 0, 16, SpriteSheet.sheet);
	public static Sprite title = new Sprite(0, 0, 144, SpriteSheet.title);

	public static Sprite f0_playerIdle = new Sprite(0, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player0 = new Sprite(1, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player1 = new Sprite(2, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player2 = new Sprite(3, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player3 = new Sprite(4, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player4 = new Sprite(5, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_player5 = new Sprite(6, 0, 32, SpriteSheet.playerSheet);
	public static Sprite f0_playerJump0 = new Sprite(0, 1, 32, SpriteSheet.playerSheet);
	public static Sprite f0_playerJump1 = new Sprite(1, 1, 32, SpriteSheet.playerSheet);
	public static Sprite f0_playerJump2 = new Sprite(2, 1, 32, SpriteSheet.playerSheet);

	public static Sprite umbrella = new Sprite(7, 2, 32, SpriteSheet.playerSheet);

	public static Sprite explode0 = new Sprite(7, 0, 32, SpriteSheet.playerSheet);
	public static Sprite explode1 = new Sprite(7, 1, 32, SpriteSheet.playerSheet);

	public static Sprite f1_playerIdle = new Sprite(0, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player0 = new Sprite(1, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player1 = new Sprite(2, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player2 = new Sprite(3, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player3 = new Sprite(4, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player4 = new Sprite(5, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_player5 = new Sprite(6, 2, 32, SpriteSheet.playerSheet);
	public static Sprite f1_playerJump0 = new Sprite(0, 3, 32, SpriteSheet.playerSheet);
	public static Sprite f1_playerJump1 = new Sprite(1, 3, 32, SpriteSheet.playerSheet);
	public static Sprite f1_playerJump2 = new Sprite(2, 3, 32, SpriteSheet.playerSheet);

	public static Sprite f2_playerIdle = new Sprite(0, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player0 = new Sprite(1, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player1 = new Sprite(2, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player2 = new Sprite(3, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player3 = new Sprite(4, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player4 = new Sprite(5, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_player5 = new Sprite(6, 4, 32, SpriteSheet.playerSheet);
	public static Sprite f2_playerJump0 = new Sprite(0, 5, 32, SpriteSheet.playerSheet);
	public static Sprite f2_playerJump1 = new Sprite(1, 5, 32, SpriteSheet.playerSheet);
	public static Sprite f2_playerJump2 = new Sprite(2, 5, 32, SpriteSheet.playerSheet);

	public static Sprite f3_playerIdle = new Sprite(0, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player0 = new Sprite(1, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player1 = new Sprite(2, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player2 = new Sprite(3, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player3 = new Sprite(4, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player4 = new Sprite(5, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_player5 = new Sprite(6, 6, 32, SpriteSheet.playerSheet);
	public static Sprite f3_playerJump0 = new Sprite(0, 7, 32, SpriteSheet.playerSheet);
	public static Sprite f3_playerJump1 = new Sprite(1, 7, 32, SpriteSheet.playerSheet);
	public static Sprite f3_playerJump2 = new Sprite(2, 7, 32, SpriteSheet.playerSheet);

	public static Sprite f4_playerIdle = new Sprite(0, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player0 = new Sprite(1, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player1 = new Sprite(2, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player2 = new Sprite(3, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player3 = new Sprite(4, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player4 = new Sprite(5, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_player5 = new Sprite(6, 8, 32, SpriteSheet.playerSheet);
	public static Sprite f4_playerJump0 = new Sprite(0, 9, 32, SpriteSheet.playerSheet);
	public static Sprite f4_playerJump1 = new Sprite(1, 9, 32, SpriteSheet.playerSheet);
	public static Sprite f4_playerJump2 = new Sprite(2, 9, 32, SpriteSheet.playerSheet);

	public static Sprite f5_playerIdle = new Sprite(0, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player0 = new Sprite(1, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player1 = new Sprite(2, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player2 = new Sprite(3, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player3 = new Sprite(4, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player4 = new Sprite(5, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_player5 = new Sprite(6, 10, 32, SpriteSheet.playerSheet);
	public static Sprite f5_playerJump0 = new Sprite(0, 11, 32, SpriteSheet.playerSheet);
	public static Sprite f5_playerJump1 = new Sprite(1, 11, 32, SpriteSheet.playerSheet);
	public static Sprite f5_playerJump2 = new Sprite(2, 11, 32, SpriteSheet.playerSheet);

	public static Sprite grass = new Sprite(0, 0, 16, SpriteSheet.sheet);
	public static Sprite dirt = new Sprite(0, 1, 16, SpriteSheet.sheet);
	public static Sprite air = new Sprite(1, 0, 16, SpriteSheet.sheet);
	public static Sprite dirtBack = new Sprite(2, 0, 16, SpriteSheet.sheet);
	public static Sprite stone = new Sprite(1, 1, 16, SpriteSheet.sheet);
	public static Sprite stoneTop = new Sprite(2, 1, 16, SpriteSheet.sheet);
	public static Sprite stoneDirtTransitionUp = new Sprite(1, 2, 16, SpriteSheet.sheet);
	public static Sprite stoneDirtTransitionRight = new Sprite(1, 3, 16, SpriteSheet.sheet);
	public static Sprite stoneDirtTransitionCorner = new Sprite(2, 2, 16, SpriteSheet.sheet);
	public static Sprite stoneDirtTransitionCornerIn = new Sprite(2, 3, 16, SpriteSheet.sheet);

	public static Sprite voidSprite = new Sprite(0, 2, 16, SpriteSheet.sheet);

	public static Sprite pizza = new Sprite(3, 0, 16, SpriteSheet.sheet);
	public static Sprite apple = new Sprite(3, 1, 16, SpriteSheet.sheet);
	public static Sprite superApple = new Sprite(5, 1, 16, SpriteSheet.sheet);
	public static Sprite donut = new Sprite(3, 2, 16, SpriteSheet.sheet);
	public static Sprite shadow = new Sprite(3, 3, 16, SpriteSheet.sheet);
	public static Sprite burger = new Sprite(4, 0, 16, SpriteSheet.sheet);
	public static Sprite coin = new Sprite(5, 0, 16, SpriteSheet.sheet);

	public static Sprite burgerMob0 = new Sprite(4, 1, 16, SpriteSheet.sheet);
	public static Sprite burgerMob1 = new Sprite(4, 2, 16, SpriteSheet.sheet);
	public static Sprite burgerMob2 = new Sprite(4, 3, 16, SpriteSheet.sheet);
	public static Sprite burgerMob3 = new Sprite(4, 4, 16, SpriteSheet.sheet);
	public static Sprite chickenSpikes = new Sprite(4, 5, 16, SpriteSheet.sheet);

	public static Sprite Smoke = new Sprite(0, 3, 16, SpriteSheet.sheet);
	public static Sprite gym = new Sprite(0, 1, 16 * 4, SpriteSheet.sheet);
	public static Sprite cloud = new Sprite(2, 0, 16 * 3, SpriteSheet.sheet);

	public Sprite(int x, int y, int size, SpriteSheet sheet) {
		this.x = x * size;
		this.y = y * size;
		this.size = size;
		this.sheet = sheet;
		pixels = new int[size * size];
		loadSprite();
	}

	public Sprite(int size, int col) {
		this.size = size;
		this.col = col;
		pixels = new int[size * size];
		getCol();
	}

	private void getCol() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = col;
			}
		}
	}

	private void loadSprite() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
