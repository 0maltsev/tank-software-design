package ru.mipt.bit.platformer.util.movement;

import com.badlogic.gdx.math.GridPoint2;

public class Movement {
    public GridPoint2 direction;
    public float rotation;

    public Movement(){
        direction = new GridPoint2(0, 0);
        rotation = 0f;
    }

    public Movement(GridPoint2 vector, float rotation){
        direction = vector;
        this.rotation = rotation;
    }


    public boolean isNull(){
        return (direction.x == 0 && direction.y == 0);
    }
}
