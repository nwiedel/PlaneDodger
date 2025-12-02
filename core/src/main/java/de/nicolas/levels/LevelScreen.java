package de.nicolas.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.nicolas.actors.*;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.game.BaseGame;
import de.nicolas.utils.screens.BaseScreen;

public class LevelScreen extends BaseScreen {

    private Plane plane;

    private float starTimer;
    private float starSpawnInterval;
    private int score;
    private Label scoreLabel;

    private float enemyTimer;
    private float enemySpawnInterval;
    private float enemySpeed;
    private boolean isGameOver;
    private BaseActor gameOverMessage;

    private Music backgroundMusic;
    private Sound sparkleSound;
    private Sound explosionSound;

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
        uiTable.add(gameOverMessage).expandY();

        enemyTimer = 0;
        enemySpeed = 100;
        enemySpawnInterval = 3;

        isGameOver = false;
        gameOverMessage = new BaseActor(0, 0, uiStage);
        gameOverMessage.loadTexture("assets/game-over.png");
        gameOverMessage.setVisible(false);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/Prelude-and-Action.mp3"));
        sparkleSound = Gdx.audio.newSound(Gdx.files.internal("assets/sparkle.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("assets/explosion.wav"));

        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1.0f);
        backgroundMusic.play();
    }

    @Override
    public void update(float delta) {

        if(isGameOver){
            return;
        }

        starTimer += delta;

        if (starTimer > starSpawnInterval){
            new Star(800, MathUtils.random(100, 500), mainStage);
            starTimer = 0;
        }

        for (BaseActor star : BaseActor.getList(mainStage, "de.nicolas.actors.Star")){

            if (plane.overlaps(star)){
                star.remove();
                score++;
                scoreLabel.setText(Integer.toString(score));

                Sparkle sparkle = new Sparkle(0, 0, mainStage);
                sparkle.centerAtActor(star);
                sparkleSound.play();
            }
        }

        enemyTimer+= delta;

        if (enemyTimer > enemySpawnInterval){
            Enemy enemy = new Enemy(800, MathUtils.random(100, 500), mainStage);
            enemy.setSpeed(enemySpeed);

            enemyTimer = 0;
            enemySpawnInterval -= 0.10f;
            enemySpeed += 10;

            if (enemySpawnInterval < 0.5f){
                enemySpawnInterval = 0.5f;
            }
            if (enemySpeed > 400){
                enemySpeed = 400;
            }
        }
        for (BaseActor enemy : BaseActor.getList(mainStage, "de.nicolas.actors.Enemy")){

            if (plane.overlaps(enemy)){
                plane.remove();
                isGameOver = true;
                gameOverMessage.setVisible(true);

                Explosion explosion = new Explosion(0, 0, mainStage);
                explosion.centerAtActor(plane);
                explosion.setScale(3);
                explosionSound.play();
                backgroundMusic.stop();
            }

            if (enemy.getY() + enemy.getWidth() < 0){
                score++;
                scoreLabel.setText(Integer.toString(score));
                enemy.remove();
            }
        }
    }

    public boolean keyDown(int keyCode){

        if (keyCode == Input.Keys.SPACE){
            plane.boost();
        }
        return true;
    }
}
