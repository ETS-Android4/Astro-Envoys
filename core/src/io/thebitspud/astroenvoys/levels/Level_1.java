package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;
import io.thebitspud.astroenvoys.weapons.ScatterA;

public class Level_1 extends Level {
	public Level_1(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Level 01";
	}

	@Override
	public String title() {
		return "01: Delivery";
	}

	@Override
	public String desc() {
		return "Pilot, we have a classified shipment that needs to be sent to the Sokar system. A number of " +
				"civilian freighters have recently disappeared here without explanation, so you will be " +
				"compensated generously if you deliver the package.";
	}

	@Override
	protected void onClear() {
		app.loadoutScreen.addSecondary(new ScatterA(game));
		app.levelSelectScreen.addLevel(new Level_2(app));
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

		timers.add(new JTimerUtil(100, true, true) {
			private boolean checked;
			@Override
			public void onActivation() {
				if (checked) game.endGame(true);
				else setTimerDuration(0.25);

				if(game.allEnemiesCleared()) {
					checked = true;
					setTimerDuration(1.0);
				}
			}
		});

		timers.add(new JTimerUtil(2.5, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(3, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(levelTime.getTimeElapsed() >= 100) {
					setActive(false);
					summon();
				}

				if(getTimerDuration() == 3) setTimerDuration(7);
				else setTimerDuration(getTimerDuration() * 0.985);

				activations++;
				summon();
				if(activations % 3 == 0) setTimeElapsed(getTimerDuration() * 0.75);
				if(activations % 8 == 0) summon();
			}

			private void summon() {
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);
			}
		});
	}
}