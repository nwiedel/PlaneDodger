package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Sparkle extends BaseActor {

    public Sparkle(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("assets/sparkle.png", 8, 8, 0.2f, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isAnimationFinished()){
            remove();
        }
    }
}
