package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import org.lwjgl.system.CallbackI;
import ru.mipt.bit.platformer.util.graphics.ScreenPicture;
import ru.mipt.bit.platformer.util.leveling.LevelCreator;
import ru.mipt.bit.platformer.util.movement.Movement;
import ru.mipt.bit.platformer.util.objects.Level;
import ru.mipt.bit.platformer.util.objects.Player;
import ru.mipt.bit.platformer.util. objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private Level levelLayer;
    private List<Player> tanks;
    private List<Tree> trees;


    int screenSide1;
    int screenSide2;
    @Override
    public void create() {
        batch = new SpriteBatch();

        levelLayer = new Level(new TmxMapLoader().load("level.tmx"), batch);

        LevelCreator levelGenerator = new LevelCreator();
        levelGenerator.generateLevelFromFile("src/main/resources/level.txt");

        screenSide1 = levelGenerator.getSide1();
        screenSide2 = levelGenerator.getSide2();

        tanks = new ArrayList<>();
        for (int i = 0; i < levelGenerator.getTankCoordinates().size(); i++){
            tanks.add(new Player(new Texture("images/tank_blue.png"), levelGenerator.getTankCoordinates().get(i)));
        }

        trees = new ArrayList<>();
        for (int i = 0; i < levelGenerator.getTreeCoordinates().size(); i++){
            trees.add(new Tree(new Texture("images/greenTree.png"), levelGenerator.getTreeCoordinates().get(i)));
        }

        levelLayer.getObstaclesPosition(trees);
    }
    @Override
    public void render() {
        // clear the screen
        ScreenPicture.clearScreen();

        tanks.get(0).updateMove(true);
        for (int i = 1; i < tanks.size(); i++) {
            tanks.get(i).updateMove(false);
        }

        List<Boolean> obstacle = new ArrayList<>();
        for (Player tank : tanks) {
            obstacle.add(tank.checkCollisions(trees, tanks, screenSide1, screenSide2));
        }
        for (int i = 0; i < obstacle.size(); i++) {
            if (!obstacle.get(i)){
                tanks.get(i).nextMove(new Movement(new GridPoint2(0, 0), tanks.get(i).getRotation()));
            }
            tanks.get(i).move(trees, tanks, MOVEMENT_SPEED);
        }


        for (Player tank : tanks) {
            tank.updateCoordinates();
        }

        for (Player tank : tanks) {
            levelLayer.updatePosition(tank);
        }


        // calculate interpolated player screen coordinates
        for (Player tank : tanks) {
            levelLayer.updatePosition(tank);
        }
        // render each tile of the level
        levelLayer.render();

        ScreenPicture.draw(batch, tanks, trees);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }
    @Override
    public void pause() {
        // game doesn't get paused
    }
    @Override
    public void resume() {
        // game doesn't get paused
    }
    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (Tree tree : trees) {
            tree.dispose();
        }
        for (Player tank : tanks) {
            tank.dispose();
        }
        levelLayer.dispose();
        batch.dispose();
    }
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}