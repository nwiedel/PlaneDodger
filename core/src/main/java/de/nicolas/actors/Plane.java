package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Plane extends BaseActor {

    public Plane(float x, float y, Stage stage) {
        super(x, y, stage);
        String[] fileNames =
            {"assets/planeGreen0.png", "assets/planeGreen1.png",
            "assets/planeGreen2.png", "assets/planeGreen1.png"};
        loadAnimationFromFiles(fileNames, 0.1f, true);

        setMaxSpeed(800);
        setBoundaryPolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // simuliert Gravitation
        setAcceleration(800);
        accelerateAtAngle(270);
        applyPhysics(delta);

        // verhindert, dass das Flugzeug tiefer als der Boden sinkt
        for (BaseActor ground : BaseActor.getList(this.getStage(), "de.nicolas.actors.Ground")){
            if (this.overlaps(ground)){
                setSpeed(0);
                preventOverlap(ground);
            }
        }

        // verhindert, dass das Flugzeug höher als der Screen fliegt
        if(getY() + getHeight() > getWorldBounds().height){
            setSpeed(0);
            boundToWorld();
        }
    }

    public void boost(){
        setSpeed(300);
        setMotionAngle(90);
    }
}
