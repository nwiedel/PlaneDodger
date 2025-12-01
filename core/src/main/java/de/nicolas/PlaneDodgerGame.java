package de.nicolas;

import com.badlogic.gdx.Screen;
import de.nicolas.levels.LevelScreen;
import de.nicolas.utils.game.BaseGame;

public class PlaneDodgerGame extends BaseGame {

    @Override
    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}
