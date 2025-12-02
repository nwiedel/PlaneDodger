package de.nicolas.levels;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.nicolas.actors.Ground;
import de.nicolas.actors.Plane;
import de.nicolas.actors.Sky;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.game.BaseGame;
import de.nicolas.utils.screens.BaseScreen;

public class LevelScreen extends BaseScreen {

    private Plane plane;

    private float starTimer;
    private float starSpawnInterval;
    private int score;
    private Label scoreLabel;

    @Override
    public void initialize() {

        new Sky(0, 0, mainStage);
        new Sky(800, 0, mainStage);

        new Ground(0, 0, mainStage);
        new Ground(800, 0, mainStage);

        plane = new Plane(100, 500, mainStage);
        BaseActor.setWorldBounds(800, 600);

        starTimer = 0;
        starSpawnInterval = 4;
        score = 0;
        scoreLabel = new Label(Integer.toString(score), BaseGame.labelStyle);
        uiTable.pad(10);
        uiTable.add(scoreLabel);
        uiTable.row();
        uiTable.add().expandY();
    }

    @Override
    public void update(float delta) {

    }

    public boolean keyDown(int keyCode){

        if (keyCode == Input.Keys.SPACE){
            plane.boost();
        }
        return true;
    }
}
