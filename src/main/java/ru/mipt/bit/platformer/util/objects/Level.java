package ru.mipt.bit.platformer.util.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.leveling.LevelCreator;
import ru.mipt.bit.platformer.util.observer.BulletAction;
import ru.mipt.bit.platformer.util.observer.Observable;
import ru.mipt.bit.platformer.util.observer.Observer;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Level implements Observable {
    public TiledMap level;
    public MapRenderer levelRenderer;
    public TileMovement tileMovement;
    public TiledMapTileLayer groundLayer;

    private final List<Bullet> bullets;
    public Level(TiledMap load, Batch batch){
        level = load;
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void updatePosition(Player player) {
        tileMovement.moveRectangleBetweenTileCenters(player.getTexture().getRectangle(),
                player.getCoordinates(),
                player.getDestination(),
                player.getMovementProgress());
    }

    public void getObstaclesPosition(List<Tree> trees) {
        for (Tree tree : trees) {
            moveRectangleAtTileCenter(groundLayer, tree.getRectangle(), tree.getCoordinates());
        }
    }

    public void render(){
        levelRenderer.render();
    }

    public void dispose(){
        level.dispose();
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        giveSignal(BulletAction.AddBullet, bullet);
    }


    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void giveSignal(BulletAction bulletAction, BaseObject object) {

    }
}

