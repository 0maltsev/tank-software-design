package ru.mipt.bit.platformer.AI.state.immovable;

public class Obstacle {
    private final int x, y; // doc 0,1

    /**
     * Represents immovable object on grid.
     *
     * @param x X-axis coordinate
     * @param y Y-axis coordinate
     */
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
