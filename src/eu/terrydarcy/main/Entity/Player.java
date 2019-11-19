package eu.terrydarcy.main.Entity;

import java.util.ArrayList;

import eu.terrydarcy.main.Game;
import eu.terrydarcy.main.Entity.ParticleSpawner.TYPE;
import eu.terrydarcy.main.gfx.Font;
import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.input.Keyboard;

public class Player extends Mob {

	private Keyboard key;
	public float speed = 2;
	public int dir = 0;
	public float v = 0, gravity = 1.2F;
	private boolean onGround = false;
	int anim = 0;
	private int fatness = 0;
	boolean canFlap = false;
	int flapTime = 0;
	private int calorieIntake = 0;
	public float GRAVITY = 0.8F;
	private int jumpsRemaining = 1, JUMPSREMAINING = 1;
	private int jumpTime = 10;
	private int explodeTime = 120;
	public float xScreen = 0;
	private final float ox, oy;
	private int time = 0;
	private boolean resetting = false;
	private boolean umbrella = false;
	private int uTime = 60;
	public int Coins = 0;

	public Player(float x, float y, Keyboard key, int pCoins) {
		this.x = x;
		this.y = y;
		this.ox = x;
		this.oy = y;
		this.key = key;
		this.sprite = Sprite.f0_playerIdle;
		this.Coins = pCoins;
	}

	public void tick() {
		time++;
		float xa = 0, ya = 0;
		if (key.left) {
			xa = -speed;
			dir = 0;

		}
		if (key.right) {
			xa = speed;
			dir = 2;

		}

		ya += v * dt;
		v += gravity * dt;

		if (v * dt > 0 && collision(0, v * dt)) {
			onGround = true;
			uTime = 0;
			jumpsRemaining = JUMPSREMAINING;
			v = 0;
		}
		if (collision(0, v * dt)) {
			v = 0;
		}

		if (jumpTime > 0) jumpTime--;
		if (!collision(0, v * dt) && jumpsRemaining > 0 && jumpTime == 0 && onGround && key.jump) {
			int choice = random.nextInt(8);
			if (choice == 0) Game.playSound("/sounds/jump0.wav", 0.0F);
			if (choice == 1) Game.playSound("/sounds/jump1.wav", 0.0F);
			if (choice == 2) Game.playSound("/sounds/jump2.wav", 0.0F);
			if (choice == 3) Game.playSound("/sounds/jump3.wav", 0.0F);
			if (choice == 4) Game.playSound("/sounds/jump4.wav", 0.0F);
			if (choice == 5) Game.playSound("/sounds/jump5.wav", 0.0F);
			if (choice == 6) Game.playSound("/sounds/jump6.wav", 0.0F);
			if (choice == 7) Game.playSound("/sounds/jump7.wav", 0.0F);
			level.add(new ParticleSpawner((int) x, (int) y, 3, 50, level, Sprite.Smoke, "", TYPE.PARTICLE));
			v -= 40.0F;
			jumpsRemaining--;
			onGround = false;
			jumpTime = 15;
		} else {
			canFlap = false;
			if (v < -7.5F) v = -7.5F;
		}

		if (!onGround) {
			flapTime++;
			if (flapTime > 20 && v > 3) canFlap = true;
		}

		if (canFlap && !onGround && key.shift && uTime < 60) {
			gravity = GRAVITY / 20;
			uTime++;
		} else {
			gravity = GRAVITY;
			canFlap = false;
		}

		if ((key.shift && onGround)) {
			gravity = GRAVITY / 2;
			umbrella = true;
		} else if (!onGround && canFlap) {
			umbrella = true;
		} else {
			umbrella = false;
		}
		if (uTime > 60) umbrella = false;

		if (onGround && xa != 0) {
			if (time % 19 == 0) {
				int choice = random.nextInt(6);
				if (choice == 0) Game.playSound("/sounds/step0.wav", 0.0F);
				if (choice == 1) Game.playSound("/sounds/step1.wav", 0.0F);
				if (choice == 2) Game.playSound("/sounds/step2.wav", 0.0F);
				if (choice == 3) Game.playSound("/sounds/step3.wav", 0.0F);
				if (choice == 4) Game.playSound("/sounds/step4.wav", 0.0F);
				if (choice == 5) Game.playSound("/sounds/step5.wav", 0.0F);
			}
		}

		if (xa == 0) {
			if (dx > 0) {
				if (dx < dt) dx = 0;
				dx -= dt;
			}
			if (dx < 0) {
				dx += dt;
			}
		}

		ArrayList<Food> ufood = level.getFood((int) x, (int) y - 32, 20);
		if (ufood.size() > 0 && umbrella) {
			for (int i = 0; i < ufood.size(); i++) {
				if (!ufood.get(i).onGround) {
					Game.playSound("/sounds/step0.wav", 5.0F);
					ufood.get(i).v -= 4.0F;
				}
			}

		}
		ArrayList<Food> food = level.getFood((int) x, (int) y, 12);
		ArrayList<Coin> coins = level.getCoins((int) x, (int) y, 12);
		if (coins.size() > 0 && explodeTime > 0) {
			for (int i = 0; i < coins.size(); i++) {
				Coins++;
				Game.playSound("/sounds/level.wav", 5.0F);
				coins.get(i).toRemove = true;
			}
		}

		if (food.size() > 0 && explodeTime > 0) {
			for (int i = 0; i < food.size(); i++) {
				if (food.get(i) instanceof Pizza) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/eating0.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/eating1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/eating2.wav", 5.0F);
					eat(Pizza.CALORIES);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, "+" + Integer.toString(Pizza.CALORIES), TYPE.TEXT));

					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof Burger) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/eating0.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/eating1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/eating2.wav", 5.0F);
					eat(Burger.CALORIES);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, "+" + Integer.toString(Burger.CALORIES), TYPE.TEXT));

					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof BurgerMob) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/eating0.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/eating1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/eating2.wav", 5.0F);
					eat(BurgerMob.CALORIES);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, "+" + Integer.toString(BurgerMob.CALORIES), TYPE.TEXT));

					if (fatness < 6) fatness++;
					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof Apple) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/apple.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/apple1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/apple2.wav", 5.0F);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, " " + Integer.toString(Apple.CALORIES), TYPE.TEXT));
					eat(Apple.CALORIES);
					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof SuperApple) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/apple.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/apple1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/apple2.wav", 5.0F);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, " " + Integer.toString(-8000), TYPE.TEXT));

					calorieIntake = 0;
					fatness = 0;

					// eat(-SuperApple.CALORIES);
					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof Donut) {
					int choice = random.nextInt(3);
					if (choice == 0) Game.playSound("/sounds/apple.wav", 5.0F);
					if (choice == 1) Game.playSound("/sounds/apple1.wav", 5.0F);
					if (choice == 2) Game.playSound("/sounds/apple2.wav", 5.0F);
					eat(Donut.CALORIES);
					level.add(new ParticleSpawner((int) x, (int) y - 16, 1, 100, level, Sprite.test, Integer.toString(Donut.CALORIES), TYPE.TEXT));

					food.get(i).toRemove = true;
				} else if (food.get(i) instanceof ChickenSpikes) {
					Game.playSound("/sounds/explosion.wav", 5.0F);
					eat(ChickenSpikes.CALORIES);
					v -= 60F;
				}
			}
		}

		if (calorieIntake > 0 && calorieIntake < 2000) fatness = 0;
		if (calorieIntake >= 2000 && calorieIntake < 3000) fatness = 1;
		if (calorieIntake >= 3000 && calorieIntake < 4000) fatness = 2;
		if (calorieIntake >= 4000 && calorieIntake < 5000) fatness = 3;
		if (calorieIntake >= 5000 && calorieIntake < 6000) fatness = 4;
		if (calorieIntake >= 6000 && calorieIntake < 7000) fatness = 5;
		if (calorieIntake >= 7000 && calorieIntake >= 8000) fatness = 6;

		if (key.music) {
			Game.music = false;
			System.out.println(key.music);
		}

		 
		
		if (key.reset) {
			resetting = true;
		}
		if (resetting) {
			calorieIntake = 8000;
			fatness = 6;
		}

		if (fatness == 0) {
			speed = 2;
			GRAVITY = 0.80F;
			JUMPSREMAINING = 2;
		}
		if (fatness == 1) {
			speed = 2;
			GRAVITY = 0.80F;
			JUMPSREMAINING = 2;
		}
		if (fatness == 2) {
			speed = 1;
			GRAVITY = 1.15F;
			JUMPSREMAINING = 2;
		}
		if (fatness == 3) {
			speed = 1;
			GRAVITY = 1.25F;
			JUMPSREMAINING = 2;
		}
		if (fatness == 4) {
			speed = 1;
			GRAVITY = 1.35F;
			JUMPSREMAINING = 1;
		}
		if (fatness == 5) {
			speed = 0.5F;
			GRAVITY = 1.56F;
			JUMPSREMAINING = 1;
		}
		if (fatness == 6) {
			speed = 0.78F;
			GRAVITY = 1.3F;
			if (calorieIntake >= 8000) {
				System.out.println(fatness);
				explodeTime--;
				if (explodeTime <= 0) explode();
			} else {
				explodeTime = 120;
			}
		}

		if (key.cheat) {
			fatness = 0;
			calorieIntake = 0;
		}
		animate(xa, ya);
		if (explodeTime > -20) move(xa, ya);
	}

	public void explode() {
		if (explodeTime > -23 && explodeTime < -21) {
			level.add(new ParticleSpawner((int) x, (int) y - 16, 10, 22200, level, Sprite.burger, "", TYPE.PARTICLE));
			level.add(new ParticleSpawner((int) x, (int) y - 16, 10, 22200, level, Sprite.pizza, "", TYPE.PARTICLE));
			Game.playSound("/sounds/explosion.wav", 5.0F);
		}
		if (explodeTime > -25) {
			sprite = Sprite.explode1;
		} else if (explodeTime < -40) {
			sprite = Sprite.explode0;
		} else if (explodeTime < -60) {
			sprite = Sprite.explode1;
		}

		if (explodeTime < -100) {
			calorieIntake = 0;
			fatness = 0;
			level.playerCoins = Coins;
			level.restartLevel();
			// toRemove = true;
		}
	}

	public void eat(int calories) {
		if (calories + calorieIntake < 0) calorieIntake = 0;

		if (calories < 0 && calorieIntake > 0) {
			calorieIntake += calories;
		}
		if (calories > 0 && calorieIntake <= 8000) {
			calorieIntake += calories;
		}
		if (calorieIntake > 8000) calorieIntake = 8000;
	}

	boolean xFlip = false;

	public void render(Render render) {
		int xOffset = 65;
		int col = 0xff579636;
		render.renderRect(0, 0, Game.W * 3, 11, 0xff383838);

		if (level.lvl == 1 && (int) x / 16 == 10 && (int) y / 16 == 9) Font.draw("AWESOME SECRET MESSAGE, THAT IS ALL", render, 16 * 3, (int) y - 3 * 16, 0xffffffff, true);

		if (calorieIntake >= 8000 && explodeTime < 120) {
			double xx = xOffset + (explodeTime * 1.5) + 25;
			if (xx <= xOffset) xx = xOffset;
			for (int i = 0; i < 32; i++) {
				int cx = calorieIntake / 32;
				col = 0xffE45050;
				render.render(xOffset + i * 8, 2, i + 2 * 32, col, false, false, false);
				render.render((int) xx, 2, 0 + 3 * 32, 0xffC387FF, false, false, false);
			}
		} else {
			for (int i = 0; i < 32; i++) {
				int cx = calorieIntake / 32;
				col = 0xff579636;
				render.render(xOffset + i * 8, 2, i + 2 * 32, col, false, false, false);
				render.render(xOffset + cx, 2, 0 + 3 * 32, 0xffC387FF, false, false, false);
			}
		}
		String c = Integer.toString(Coins);
		render.renderSpriteToBack(320, -2, Sprite.coin, false, false);

		Font.draw("x", render, 332, 2, col, false);
		Font.draw("x", render, 332, 1, 0xffffff, false);
		Font.draw(c, render, 338, 2, col, false);
		Font.draw(c, render, 338, 1, 0xffffff, false);

		Font.draw("Calories", render, 0, 2, col, false);
		Font.draw("Calories", render, 0, 1, 0xffffffff, false);
		if (explodeTime > -20 && dir == 0) {
			xFlip = true;
		} else {
			xFlip = false;
		}
		if (umbrella && onGround) {
			if (xFlip) {
				render.renderSprite((int) x - 26, (int) y - 31, Sprite.umbrella, xFlip, false);
			} else {
				render.renderSprite((int) x - 8, (int) y - 30, Sprite.umbrella, xFlip, false);
			}
		} else if (umbrella && !onGround) {
			if (xFlip) {
				render.renderSprite((int) x - 2, (int) y - 35, Sprite.umbrella, !xFlip, false);
			} else {
				render.renderSprite((int) x - 30, (int) y - 35, Sprite.umbrella, !xFlip, false);
			}
		}
		render.renderSprite((int) x - 16, (int) y - 16, sprite, xFlip, false);
	}

	private void animate(float xa, float ya) {
		if ((xa > 0 || xa < 0) && anim < Integer.MAX_VALUE - 10) {
			anim++;
		} else {
			anim = 0;
		}

		int frames = 6;
		int rate = frames * 8;
		if (explodeTime > -20) {
			if (fatness == 0) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f0_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f0_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f0_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f0_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f0_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f0_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f0_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f0_playerJump1;
					if (v * dt > 1) sprite = Sprite.f0_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f0_playerIdle;
				}
			} else if (fatness == 1) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f1_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f1_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f1_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f1_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f1_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f1_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f1_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f1_playerJump1;
					if (v * dt > 1) sprite = Sprite.f1_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f1_playerIdle;
				}
			} else if (fatness == 2) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f2_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f2_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f2_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f2_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f2_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f2_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f2_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f2_playerJump1;
					if (v * dt > 1) sprite = Sprite.f2_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f2_playerIdle;
				}
			} else if (fatness == 3) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f3_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f3_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f3_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f3_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f3_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f3_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f3_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f3_playerJump1;
					if (v * dt > 1) sprite = Sprite.f3_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f3_playerIdle;
				}
			} else if (fatness == 4) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f4_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f4_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f4_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f4_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f4_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f4_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f4_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f4_playerJump1;
					if (v * dt > 1) sprite = Sprite.f4_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f4_playerIdle;
				}
			} else if (fatness == 5 || fatness == 6) {
				if (onGround) {
					if (anim % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.f5_player5;
					else if (anim % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.f5_player4;
					else if (anim % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.f5_player3;
					else if (anim % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.f5_player2;
					else if (anim % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.f5_player1;
					else if (anim % rate * 6 > (rate * 6) - (rate * 6)) sprite = Sprite.f5_player0;
				} else {
					if (v * dt < 0) sprite = Sprite.f5_playerJump0;
					if (v * dt > 0 && v * dt < 1) sprite = Sprite.f5_playerJump1;
					if (v * dt > 1) sprite = Sprite.f5_playerJump2;
				}
				if (xa == 0 && v * dt == 0) {
					sprite = Sprite.f5_playerIdle;
				}
			}
		} else {
			if (explodeTime % rate * 6 > (rate * 6) - (rate * 1)) sprite = Sprite.explode1;
			if (explodeTime % rate * 6 > (rate * 6) - (rate * 2)) sprite = Sprite.explode0;
			if (explodeTime % rate * 6 > (rate * 6) - (rate * 3)) sprite = Sprite.explode1;
			if (explodeTime % rate * 6 > (rate * 6) - (rate * 4)) sprite = Sprite.explode0;
			if (explodeTime % rate * 6 > (rate * 6) - (rate * 5)) sprite = Sprite.explode1;
		}
	}
}
