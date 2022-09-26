package ru.mipt.bit.platformer.util.enums;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(0, 1, 90f),
    DOWN(0, -1, -90f),
    LEFT(-1, 0, -180f),
    RIGHT(1, 0, 0f);

    public GridPoint2 vector = new GridPoint2(0, 0);
    public float rotation;

    Direction(int x, int y, float rotation){
        vector.x = x;
        vector.y = y;
        this.rotation = rotation;
    }
}
