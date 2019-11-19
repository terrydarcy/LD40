package eu.terrydarcy.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

import eu.terrydarcy.main.gfx.Render;
import eu.terrydarcy.main.input.Keyboard;
import eu.terrydarcy.main.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int W = 350, H = W / 16 * 10, SCALE = 3;
	private static String title = "LD40 - 'Calories'";
	private boolean running = false;
	private Thread thread;
	private Render render;
	private JFrame frame;
	private Level level;
	private Keyboard keyboard;

	private BufferedImage image = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(W * SCALE, H * SCALE);
		setPreferredSize(size);
		frame = new JFrame();
		render = new Render(W, H, pixels);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		// level = new Level(50, 25, keyboard);
		level = new Level(keyboard, 3);
	}

	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static float VOL_MOD = 0;

	public static void playSound(final String url, Float vol) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResource(url));
			clip.open(inputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(vol + VOL_MOD - 15);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				frame.setTitle(title + "     [ TICKS: " + ticks + "  |  FPS: " + frames + " ]");
				// System.out.println("TICKS: " + ticks + " | FPS: " + frames);
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}

	int time = 0;
	public static boolean music = true;

	public void tick() {
		if (music)
			if(time % (60 * 30) == 0) {
			Game.playSound("/sounds/music.wav", -10.0F);
		}
		time++;
		level.tick();
		keyboard.tick();
	}

	int xScroll, yScroll;

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		render.clear();
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		if (level.playerAlive) {
			xScroll = (int) (level.players.get(0).x - render.w / 2);
			yScroll = (int) ((level.players.get(0).y - 10 - level.players.get(0).dy) - render.h / 2);
		}
		level.render(render, xScroll, yScroll);
		// render.renderRect(0, 0, W, H, 0xF564F5);
		// render.render(0, 0, SpriteSheet.sheetTest);
		// render.renderSprite(10, 10, Sprite.test, false, false);
		g.setColor(Color.black);
		g.drawString("CALORIE INTAKE", 0, 0);
		g.drawImage(image, 2, 2, getWidth() - 4, getHeight() - 4, null);
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		Game g = new Game();
		g.frame.setResizable(false);
		g.frame.add(g);
		g.frame.pack();
		g.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.frame.setLocationRelativeTo(null);
		g.frame.setVisible(true);
		g.frame.setTitle(title);

		g.start();
	}

}
