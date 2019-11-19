package eu.terrydarcy.main.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import eu.terrydarcy.main.Game;
import eu.terrydarcy.main.Entity.Apple;
import eu.terrydarcy.main.Entity.Burger;
import eu.terrydarcy.main.Entity.BurgerMob;
import eu.terrydarcy.main.Entity.ChickenSpikes;
import eu.terrydarcy.main.Entity.Cloud;
import eu.terrydarcy.main.Entity.Coin;
import eu.terrydarcy.main.Entity.Donut;
import eu.terrydarcy.main.Entity.Entity;
import eu.terrydarcy.main.Entity.Food;
import eu.terrydarcy.main.Entity.Gym;
import eu.terrydarcy.main.Entity.Particle;
import eu.terrydarcy.main.Entity.ParticleSpawner;
import eu.terrydarcy.main.Entity.Pizza;
import eu.terrydarcy.main.Entity.Player;
import eu.terrydarcy.main.Entity.SuperApple;
import eu.terrydarcy.main.Entity.TextParticle;
import eu.terrydarcy.main.gfx.Font;
import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.input.Keyboard;
import eu.terrydarcy.main.level.Tiles.Tile;

public class Level {

	public int w;
	public int h;
	private int[] data;
	private Random random = new Random();
	private Keyboard keyboard;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private ArrayList<Food> food = new ArrayList<Food>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public ArrayList<Coin> coins = new ArrayList<Coin>();
	public boolean playerAlive = false;
	String path = "";
	private int foodDropRate = 0;
	public int lvl = 0;
	private int time = 0;
	public int playerCoins = 0;
	private boolean gameFinished = false;
	private int tickCount = 0;

	public Level(Keyboard keyboard, int lvl) {
		this.keyboard = keyboard;
		this.lvl = lvl;
		switchLevel(lvl);
	}

	public void switchLevel(int lvl) {
		if (lvl == 0) {
			Game.playSound("/sounds/level.wav", 5.0F);
			this.lvl = lvl;
			this.w = 100;
			this.h = 50;
			foodDropRate = 7;
			time = 0;
			path = "/levels/level0.csv";
		}
		if (lvl == 1) {
			Game.playSound("/sounds/level.wav", 5.0F);
			this.lvl = lvl;
			this.w = 50;
			this.h = 25;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level1.csv";
		}

		if (lvl == 2) {
			Game.playSound("/sounds/level.wav", 5.0F);

			this.lvl = lvl;
			this.w = 50;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level2.csv";
		}
		if (lvl == 3) {
			Game.playSound("/sounds/level.wav", 5.0F);

			this.lvl = lvl;
			this.w = 50;
			this.h = 50;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level3.csv";
		}
		if (lvl == 4) {
			Game.playSound("/sounds/level.wav", 5.0F);

			this.lvl = lvl;
			this.w = 100;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level4.csv";
		}
		if (lvl == 5) {
			Game.playSound("/sounds/level.wav", 5.0F);

			this.lvl = lvl;
			this.w = 100;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level5.csv";
		}
		if (lvl == 6) {
			clearLevel();
			gameFinished = true;
			time = 0;
		}
		if (!gameFinished) {
			if (playerAlive) {
				playerCoins = players.get(0).Coins;
			}
			String line = "";
			int row = 0;
			data = new int[w * h];
			clearLevel();
			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			try {
				while ((line = br.readLine()) != null) {
					String[] cols = line.split(",");
					for (int x = 0; x < w; x++) {
						data[x + row * w] = Integer.parseInt(cols[x]);
					}
					row++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			generateGym();
			generateClouds();
			generateMobs();
			playerAlive = true;
		}
	}

	public void restartLevel() {
		Game.playSound("/sounds/level.wav", 5.0F);
		time = 0;
		clearLevel();
		if (lvl == 0) {
			this.w = 100;
			this.h = 50;
			foodDropRate = 15;

			path = "/levels/level0.csv";
		}
		if (lvl == 1) {
			this.w = 50;
			this.h = 25;
			foodDropRate = 30;

			path = "/levels/level1.csv";
		}

		if (lvl == 2) {
			this.w = 50;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level2.csv";
		}
		if (lvl == 3) {
			this.w = 50;
			this.h = 50;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level3.csv";
		}
		if (lvl == 4) {
			this.w = 100;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level4.csv";
		}
		if (lvl == 5) {
			this.w = 100;
			this.h = 100;
			foodDropRate = 15;
			time = 0;
			path = "/levels/level5.csv";
		}

		String line = "";
		int row = 0;
		data = new int[w * h];
		InputStream in = getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(",");
				for (int x = 0; x < w; x++) {
					data[x + row * w] = Integer.parseInt(cols[x]);
				}
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		generateGym();
		generateClouds();
		generateMobs();
		playerAlive = true;
	}

	public void clearLevel() {
		playerAlive = false;
		players.clear();
		entities.clear();
		particles.clear();
		players.clear();
		food.clear();
		clouds.clear();
		coins.clear();
		for (int i = 0; i < data.length; i++) {
			data[i] = 999;
		}

	}

	public void tick() {
		if (!gameFinished) time++;
		 if (gameFinished && (tickCount % 10) == 0) time++;

		tickCount++;
		// switchLevel(6);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
			if (entities.get(i).toRemove) entities.remove(i);
		}
		for (int i = 0; i < food.size(); i++) {
			food.get(i).tick();
			if (food.get(i).toRemove) food.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
			// if (particles.get(i).toRemove) particles.remove(i);
		}
		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).tick();
		}
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).tick();
			if (coins.get(i).toRemove) coins.remove(i);

		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).tick();
			if (players.get(i).toRemove) {
				playerAlive = false;
				players.remove(players.get(i));
			}
		}
		if (!gameFinished) {
			if (random.nextInt(foodDropRate) == 0) {
				if (random.nextInt(25) == 0) add(new Pizza(random.nextInt(w * 16), -100));
				if (random.nextInt(30) == 0) add(new BurgerMob(random.nextInt(w * 16), -100));
				if (random.nextInt(10) == 0) add(new Burger(random.nextInt(w * 16), -100));
				if (random.nextInt(5) == 0) add(new Apple(random.nextInt(w * 16), -100));
				if (random.nextInt(100) == 0) add(new Apple(random.nextInt(w * 16), -100));
				if (random.nextInt(50) == 0) add(new Donut(random.nextInt(w * 16), -100));
			}
		} else {
			if (random.nextInt(foodDropRate) == 0) {
				if (random.nextInt(25) == 0) add(new Pizza(-w * 16 + random.nextInt(w * 16), -100));
				if (random.nextInt(30) == 0) add(new BurgerMob(-w * 16 + random.nextInt(w * 16), -100));
				if (random.nextInt(10) == 0) add(new Burger(-w * 16 + random.nextInt(w * 16), -100));
				if (random.nextInt(5) == 0) add(new Apple(-w * 16 + random.nextInt(w * 16), -100));
				if (random.nextInt(100) == 0) add(new Apple(-w * 16 + random.nextInt(w * 16), -100));
				if (random.nextInt(50) == 0) add(new Donut(-w * 16 + random.nextInt(w * 16), -100));
			}
		}
	}

	public ArrayList<Food> getFood(int x, int y, int r) {
		ArrayList<Food> result = new ArrayList<Food>();
		for (int i = 0; i < food.size(); i++) {
			if (Math.sqrt((food.get(i).x - x) * (food.get(i).x - x) + (food.get(i).y - y) * (food.get(i).y - y)) < r) result.add(food.get(i));
		}
		return result;
	}

	public ArrayList<Coin> getCoins(int x, int y, int r) {
		ArrayList<Coin> result = new ArrayList<Coin>();
		for (int i = 0; i < coins.size(); i++) {
			if (Math.sqrt((coins.get(i).x - x) * (coins.get(i).x - x) + (coins.get(i).y - y) * (coins.get(i).y - y)) < r) result.add(coins.get(i));
		}
		return result;
	}

	public void add(Entity e) {
		// if (e instanceof Food) food.add((Food) e);
		if (e instanceof Pizza) food.add((Pizza) e);
		if (e instanceof Apple) food.add((Apple) e);
		if (e instanceof Burger) food.add((Burger) e);
		if (e instanceof BurgerMob) food.add((BurgerMob) e);
		if (e instanceof Donut) food.add((Donut) e);
		if (e instanceof ChickenSpikes) food.add((ChickenSpikes) e);
		if (e instanceof SuperApple) food.add((SuperApple) e);
		if (e instanceof Coin) coins.add((Coin) e);

		if (e instanceof Particle) entities.add((Particle) e);
		if (e instanceof TextParticle) entities.add((TextParticle) e);
		if (e instanceof ParticleSpawner) entities.add(e);
		if (e instanceof Gym) entities.add(e);
		if (e instanceof Player) players.add((Player) e);
		if (e instanceof Cloud) clouds.add((Cloud) e);
		e.init(this);
	}

	public void remove(Entity e) {
		if (e instanceof ParticleSpawner) entities.remove(e);
		if (e instanceof Particle) particles.remove(e);
		if (e instanceof Food) food.remove(e);

	}

	int yy = 0;

	public void render(Render render, int xScroll, int yScroll) {
		render.setOffsets(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (render.w + xScroll) + 16 >> 4;
		int y0 = yScroll >> 4;
		int y1 = (render.h + yScroll) + 16 >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, render, this);
			}
		}
		if (lvl == 0 && !gameFinished) {// && time<1000) {
			render.renderSprite(30,23*16+8, Sprite.title,false, false);
			String intro = "BEWARE OF RAINING FOOD,";
			String intro2 = "Make it to the end of the level";
			String intro3 = "as skinny as you can";
			Font.draw(intro, render, 0, (18 * 2) * 8 - 8, 0xff404040, true);
			Font.draw(intro2, render, 0, (18 * 2) * 8 + 9, 0xff404040, true);
			Font.draw(intro3, render, 0, (18 * 2) * 8 + 18, 0xff404040, true);

			String hint1 = "'W' or UP arrow to jump";
			Font.draw(hint1, render, 16 * 16, (27 * 2) * 8 -25, 0xff404040, true);
			String hint44 = "Hold Space/Shift to glide";
			Font.draw(hint44, render, 16 * 16, (28 * 2) * 8 -25, 0xff404040, true);
			String hint2 = "Make it to the gym at the end of the level";
			String hint2_5 = "       Before exploding of obesity";
			Font.draw(hint2, render, 55 * 16, (27 * 2) * 8 - 8, 0xff404040, true);
			Font.draw(hint2_5, render, 55 * 16, (27 * 2) * 8 + 3, 0xff404040, true);

			String hint3 = "'R' to restart";
			Font.draw(hint3, render, 28 * 16 , (32 * 2) * 8 - 8, 0xff404040, true);

		}

		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).render(render);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(render);
		}

		for (int i = 0; i < food.size(); i++) {
			food.get(i).render(render);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(render);
		}
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).render(render);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(render);
		}
//		switchLevel(6);
		if (gameFinished) {
			yy = Game.H - time;
			if (yy + 16 * 8 < 0) {
				Font.draw("THE END.", render, Game.W / 2 - 8 * 4, Game.H / 2, 0, false);
			}
			// int col = 0xff101010;
			// Font.draw("'Congratulations!' - Post Malone 2016", render, 0, yy-1, col,
			// false);
			// Font.draw("This was a Ludum Dare 40 Entry", render, 0, yy + 16-1, col,
			// false);
			// Font.draw("By terry d'arcy", render, 0, yy + 32-1, col, false);
			// Font.draw("This was my third Ludum Dare", render, 0, yy + 48-1, col, false);
			// Font.draw("Please visit my website", render, 0, yy + 64-1, col, false);
			// Font.draw("Where i post frequent updates of my life", render, 0, yy + 64 +
			// 16-1, col, false);
			// Font.draw("...........", render, 0, yy + 64 + 32-1, col, false);
			// Font.draw("and stuff.", render, 0, yy + 64 + 48-1, col, false);
			// Font.draw("WWW.TERRYDARCY.EU", render, 0, yy + 64 + 64-1, col, false);
			render.renderSpriteToBack(3, yy-(45+8), Sprite.title, false, false);
			Font.draw("'Congratulations!' - Post Malone 2016", render, 0, yy, 0, false);
			Font.draw("This was a Ludum Dare 40 Entry", render, 0, yy + 16, 0, false);
			Font.draw("By terry d'arcy", render, 0, yy + 32, 0, false);
			Font.draw("This was my third Ludum Dare", render, 0, yy + 48, 0, false);
			Font.draw("Please visit my website", render, 0, yy + 64, 0, false);
			Font.draw("Where i post frequent updates of my life", render, 0, yy + 64 + 16, 0, false);
			Font.draw("...........", render, 0, yy + 64 + 32, 0, false);
			Font.draw("and stuff.", render, 0, yy + 64 + 48, 0, false);
			Font.draw("WWW.TERRYDARCY.EU", render, 0, yy + 64 + 64, 0, false);
			Font.draw("Thanks For Playing", render, 0, yy + 64 + 64 + 16, 0, false);

		}
	}

	public void generateGym() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (data[x + y * w] == 49) add(new Gym(x * 16, y * 16));
			}
		}
	}

	public void generateClouds() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (data[x + y * w] == 6) add(new Cloud(x * 16, y * 16));
			}
		}
	}

	public void generateMobs() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (data[x + y * w] == 4) add(new Burger((x * 16) + 8, y * 16));
				if (data[x + y * w] == 16) add(new BurgerMob((x * 16) + 8, y * 16));
				if (data[x + y * w] == 15) add(new Apple((x * 16) + 8, y * 16));
				if (data[x + y * w] == 17) add(new SuperApple((x * 16) + 8, y * 16));
				if (data[x + y * w] == 27) add(new Donut((x * 16) + 8, y * 16));
				if (data[x + y * w] == 3) add(new Pizza((x * 16) + 8, y * 16));
				if (data[x + y * w] == 64) add(new ChickenSpikes((x * 16) + 8, y * 16));
				if (data[x + y * w] == 5) add(new Coin((x * 16) + 8, y * 16));
				if (data[x + y * w] == 65) add(new Player(x * 16, y * 16, keyboard, playerCoins));
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= w || y < 0 || y >= h) return Tile.air;
		if (data[x + y * w] == 1) return Tile.air;
		if (data[x + y * w] == 2) return Tile.dirtBackTile;
		if (data[x + y * w] == 0) return Tile.grass;
		if (data[x + y * w] == 12) return Tile.dirt;
		if (data[x + y * w] == 13) return Tile.stone;
		if (data[x + y * w] == 14) return Tile.stoneTop;
		if (data[x + y * w] == 33) return Tile.air;
		if (data[x + y * w] == 49) return Tile.air;
		if (data[x + y * w] == 4) return Tile.dirtBackTile;
		if (data[x + y * w] == 17) return Tile.dirtBackTile;
		if (data[x + y * w] == 16) return Tile.dirtBackTile;
		if (data[x + y * w] == 15) return Tile.dirtBackTile;
		if (data[x + y * w] == 27) return Tile.dirtBackTile;
		if (data[x + y * w] == 3) return Tile.dirtBackTile;
		if (data[x + y * w] == 64) return Tile.dirtBackTile;
		if (data[x + y * w] == 65) return Tile.air;
		if (data[x + y * w] == 5) return Tile.dirtBackTile;
		return Tile.air;
	}

}
