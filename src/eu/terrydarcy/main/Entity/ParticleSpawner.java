package eu.terrydarcy.main.Entity;

import eu.terrydarcy.main.gfx.Sprite;
import eu.terrydarcy.main.level.Level;

public class ParticleSpawner extends Entity {
	public enum TYPE {
		TEXT, PARTICLE

	}

	private TYPE type;

	public ParticleSpawner(int x, int y, int amount, int lifeTime, Level level, Sprite sprite, String msg,TYPE type) {
		this.x = x;
		this.y = y;
		this.type = type;
		init(level);
		if (type == TYPE.PARTICLE) {
			for (int i = 0; i < amount; i++) {
				level.add(new Particle(x, y, lifeTime, sprite));
				level.remove(this);
			}
		}
		if (type == TYPE.TEXT) {
			for (int i = 0; i < amount; i++) {
				level.add(new TextParticle(x, y, lifeTime, msg));
				level.remove(this);
			}
		}
		
	}

}
