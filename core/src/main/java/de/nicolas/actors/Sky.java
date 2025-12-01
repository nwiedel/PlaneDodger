package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Sky extends BaseActor {


    public Sky(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("assets/sky.png");
        setSpeed(25);
        setMotionAngle(180);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        applyPhysics(delta);

        // wenn das Bild den Screen verlässt, verschiebe an die rechte Seite des zweiten Bildes
        if (getX() + getWidth() < 0){
            moveBy(2 * getWidth(), 0);
        }
    }
}
