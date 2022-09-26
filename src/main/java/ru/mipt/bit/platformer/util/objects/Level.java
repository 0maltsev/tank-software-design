package ru.mipt.bit.platformer.util.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Level{
    public TiledMap level;
    public MapRenderer levelRenderer;
    public TileMovement tileMovement;
    public TiledMapTileLayer groundLayer;

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

    public void getObstaclesPosition(Tree tree) {
        moveRectangleAtTileCenter(groundLayer, tree.getRectangle(), tree.getCoordinates());
    }

    public void render(){
        levelRenderer.render();
    }

    public void dispose(){
        level.dispose();
    }

}

