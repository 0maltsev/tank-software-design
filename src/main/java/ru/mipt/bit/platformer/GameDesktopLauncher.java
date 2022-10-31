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
import ru.mipt.bit.platformer.util.graphics.ScreenPicture;
import ru.mipt.bit.platformer.util.leveling.LevelCreator;
import ru.mipt.bit.platformer.util.objects.Level;
import ru.mipt.bit.platformer.util.objects.Player;
import ru.mipt.bit.platformer.util. objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private Level levelLayer;
    private Player tank;
    private List<Tree> trees;

    @Override
    public void create() {
        batch = new SpriteBatch();

        levelLayer = new Level(new TmxMapLoader().load("level.tmx"), batch);

        LevelCreator levelGenerator = new LevelCreator();
        levelGenerator.generateLevelFromFile("src/main/resources/level.txt");

        tank = new Player(new Texture("images/tank_blue.png"), levelGenerator.getTankCoordinates().get(0));

        //trees = new Tree(new Texture("images/greenTree.png"), new GridPoint2(1, 3));
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

        tank.move(Gdx.input, trees, MOVEMENT_SPEED);
        // calculate interpolated player screen coordinates
        levelLayer.updatePosition(tank);
        // render each tile of the level
        levelLayer.render();

        ScreenPicture.draw(batch, tank, trees);
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
        tank.dispose();
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