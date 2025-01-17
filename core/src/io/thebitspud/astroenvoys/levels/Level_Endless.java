package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_Endless extends Level {
	public Level_Endless(AstroEnvoys app) {
		super(app);
	}

	private long highScore = 0;
	private long currentScore;

	@Override
	public String id() {
		return "Endless";
	}

	@Override
	public String title() {
		return "High Score: " + highScore;
	}

	@Override
	public String desc() {
		return "Test your skills and your resolve by surviving for as long as you can in this " +
				"limitless onslaught.";
	}

	@Override
	protected void onClear() {
		// Level cannot be cleared.
	}

	@Override
	protected void addEvents() {
		currentScore = 0;
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

		timers.add(new JTimerUtil(3, true, true) {
			@Override
			public void onActivation() {
				currentScore += 1;
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
				if(getTimerDuration() > 1.0) setTimerDuration(getTimerDuration() * 0.99);
			}
		});

		timers.add(new JTimerUtil(2, true, true) {
			@Override
			public void onActivation() {
				currentScore += 10;
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);

				if(getTimerDuration() == 2) setTimerDuration(6);
				else if(getTimerDuration() > 2.5) setTimerDuration(getTimerDuration() * 0.985);
			}
		});

		timers.add(new JTimerUtil(20, true, true) {
			@Override
			public void onActivation() {
				currentScore += 30;
				int x = r.nextInt(scrWidth * 3/5) + scrWidth / 5 - 75;
				game.spawnEnemy(x, y, EntityID.AZ_HUNTER);
				if(getTimerDuration() > 6.0) setTimerDuration(getTimerDuration() * 0.965);
			}
		});

		timers.add(new JTimerUtil(30, true, true) {
			@Override
			public void onActivation() {
				currentScore += 40;
				game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_SNIPER);
				if(getTimerDuration() > 8.0) setTimerDuration(getTimerDuration() * 0.95);
			}
		});

		timers.add(new JTimerUtil(50, true, true) {
			@Override
			public void onActivation() {
				currentScore += 50;
				game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_PREDATOR);
				if(getTimerDuration() > 10.0) setTimerDuration(getTimerDuration() * 0.92);
			}
		});

		timers.add(new JTimerUtil(200, true, true) {
			@Override
			public void onActivation() {
				currentScore += 250;
				game.spawnEnemy(scrWidth / 2 - 90, y, EntityID.AZ_REAPER);
				if(getTimerDuration() > 25.0) setTimerDuration(getTimerDuration() * 0.85);
			}
		});
	}

	public void setHighScore() {
		if (currentScore > highScore) {
			highScore = currentScore;
		}
	}

	public long getLastScore() {
		return currentScore;
	}
}