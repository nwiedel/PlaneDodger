package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Enemy extends BaseActor {

    public Enemy(float x, float y, Stage stage) {
        super(x, y, stage);

        String [] fileNames =
            {"assets/planeRed0.png", "assets/planeRed1.png",
                "assets/planeRed2.png", "assets/planeRed1.png"};

        loadAnimationFromFiles(fileNames, 0.1f, true);

        setSpeed(100);
        setMotionAngle(180);
        setBoundaryPolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);
    }
}
