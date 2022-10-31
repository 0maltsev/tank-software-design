package ru.mipt.bit.platformer.util.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.graphics.TankGraphics;
import ru.mipt.bit.platformer.util.movement.*;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Player {

    private GridPoint2 coordinates;
    private GridPoint2 destination;
    private float rotation;
    private TankGraphics texture;
    private float movementProgress;


    public Movement nextMove;

    public Player(Texture tankTexture, GridPoint2 destinationCoordinates){
        texture = new TankGraphics(tankTexture);
        this.destination = destinationCoordinates;
        coordinates = new GridPoint2(destinationCoordinates);
        rotation = 0f;
        movementProgress = 1f;
        nextMove = new Movement();
    }

    public boolean finishCheck() {
        return isEqual(movementProgress, 1f);
    }

    public void rotate() {
        rotation = nextMove.rotation;
    }

    public void move() {
        destination.add(nextMove.direction);
    }

    public GridPoint2 tryMovement() {
        GridPoint2 newCoordinates = new GridPoint2();
        newCoordinates.x = destination.x + nextMove.direction.x;
        newCoordinates.y = destination.y + nextMove.direction.y;
        return newCoordinates;
    }

    public void finishMove() {
        nextMove.direction.x = 0;
        nextMove.direction.y = 0;
        movementProgress = 0f;
    }

    public boolean notObstacleAhead(List<Tree> trees) {
        GridPoint2 possibleCoordinates = tryMovement();
        for (Tree tree : trees) {
            if (tree.getCoordinates().equals(possibleCoordinates)){
                return false;
            }
        }
        return true;
    }


    public void updateMovementProgress(float deltaTime, float movementSpeed) {
        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
    }

    public float getRotation() {
        return rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestination() {
        return destination;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public TankGraphics getTexture() {
        return texture;
    }

    public void dispose(){
        texture.getBlueTank().dispose();
    }

    public void move(Input input, List<Tree> trees, float movementSpeed) {
        nextMove = KeyboardInterpreter.determineDirectionByKey(Gdx.input);
        if (!nextMove.isNull() && finishCheck()){
            rotate();
            if (notObstacleAhead(trees)){
                move();
                finishMove();
            }
        }
        float deltaTime = Gdx.graphics.getDeltaTime();
        updateMovementProgress(deltaTime, movementSpeed);
        if (finishCheck()) {
            coordinates.set(destination);
        }
    }

}

