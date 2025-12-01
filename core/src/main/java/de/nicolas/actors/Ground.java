package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Ground extends BaseActor {

    public Ground(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("assets/ground.png");
        setSpeed(100);
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
