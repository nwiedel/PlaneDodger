package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import de.nicolas.utils.actors.BaseActor;

public class Star extends BaseActor {

    public Star(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("assets/star.png");

        Action pulse = Actions.sequence(
            Actions.scaleTo(1.2f, 1.2f, 0.5f),
            Actions.scaleTo(1.0f,1.0f, 0.5f)
        );
        addAction(Actions.forever(pulse));

        setSpeed(100);
        setMotionAngle(180);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);

        // Stern entfernen, wenn er die linke Seite überschritten hat
        if (getX() + getWidth() < 0){
            remove();
        }
    }
}
