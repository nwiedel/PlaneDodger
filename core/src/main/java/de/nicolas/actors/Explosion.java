package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Explosion extends BaseActor {

    public Explosion(float x, float y, Stage stage) {
        super(x, y, stage);

        loadAnimationFromSheet("Assets/explosion.png", 6, 6, 0.2f, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isAnimationFinished()){
            remove();
        }
    }
}
