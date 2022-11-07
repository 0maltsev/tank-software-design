package ru.mipt.bit.platformer.AI.state.movable;

import ru.mipt.bit.platformer.AI.state.GameState;
import ru.mipt.bit.platformer.AI.state.movable.Orientation;

public class Actor {
    private final Object source;
    private final int x, y;
    private final int destX, destY;
    private final Orientation orientation;

    /**
     * Base class representing moving object on grid.
     *
     * @param source      object source of the data in external system
     * @param x           current X-axis coordinate between 0 and {@link GameState#getLevelWidth()}
     * @param y           current Y-axis coordinate between 0 and {@link GameState#getLevelHeight()}
     * @param destX       target X-axis coordinate where the actor is moving between 0 and {@link GameState#getLevelWidth()}
     * @param destY       target Y-axis coordinate where the actor is moving between 0 and {@link GameState#getLevelHeight()}
     * @param orientation relative to x, y coordinates
     */
    protected Actor(Object source, int x, int y, int destX, int destY, Orientation orientation) {
        this.source = source;
        this.x = x;
        this.y = y;
        this.destX = destX;
        this.destY = destY;
        this.orientation = orientation;
    }

    public boolean isMoving() {
        return x != destX || y != destY;
    }

    public Object getSource() {
        return source;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
