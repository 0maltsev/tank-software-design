package ru.mipt.bit.platformer.util.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class Tree {
    private final Texture texture;
    private final TextureRegion graphics;
    private final GridPoint2 coordinates;
    private final Rectangle rectangle;

    public Tree(Texture texture, GridPoint2 gridPoint2){
        this.texture = texture;
        graphics = new TextureRegion(texture);
        coordinates = gridPoint2;
        rectangle = createBoundingRectangle(graphics);
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose(){
        texture.dispose();
    }
}
