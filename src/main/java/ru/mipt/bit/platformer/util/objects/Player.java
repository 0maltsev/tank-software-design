package ru.mipt.bit.platformer.util.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.enums.Direction;
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
    public TileMovement tileMovement;

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

    public void moving() {
        destination.add(nextMove.direction);
    }

    public GridPoint2 tryMovement() {
        if (nextMove.isNull())
            return destination;

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


    public void updateMove(boolean itIsPlayer) {
        if (itIsPlayer){
            nextMove = KeyboardInterpreter.determineDirectionByKey(Gdx.input);
        }
        else {
            nextMove = KeyboardInterpreter.randomMovementGenerator(Gdx.input);
        }
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

    public void move(List<Tree> trees, List<Player> tanks, float movementSpeed) {
        if (!nextMove.isNull() && finishCheck()) {
            rotate();
            moving();
            finishMove();
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        updateMovementProgress(deltaTime, movementSpeed);
    }

    private void updateMovementProgress(float deltaTime, float movementSpeed) {
        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
    }

    public void updateCoordinates(){
        if (finishCheck()) {
            coordinates.set(destination);
        }
    }

    public Boolean checkCollisions(List<Tree> trees, List<Player> tanks, int screenSide1, int screenSide2) {
        return notObstacleAhead(trees) && notTankAhead(tanks) && noWallAhead(screenSide1, screenSide2);
    }

    private boolean notTankAhead(List<Player> tanks) {
        GridPoint2 thisPossibleCoordinates = tryMovement();
        GridPoint2 tankPossibleCoordinates;
        for (Player tank : tanks) {
            if (this.equals(tank)) {
                continue;
            }
            tankPossibleCoordinates = tank.tryMovement();
            if (tank.getCoordinates().equals(thisPossibleCoordinates) ||
                    tankPossibleCoordinates.equals(thisPossibleCoordinates)){
                return false;
            }
        }
        return true;
    }

    private boolean noWallAhead(int screenSide1, int screenSide2) {
        GridPoint2 nextCoordinates = tryMovement();
        return nextCoordinates.x >= 0 && nextCoordinates.x < screenSide1 &&
                nextCoordinates.y >= 0 && nextCoordinates.y < screenSide2;
    }

    public void nextMove(Movement nextMove) {
        this.nextMove = nextMove;
    }

    public void moveUp(){
        nextMove = new Movement(new GridPoint2(Direction.UP.vector), Direction.UP.rotation);
    }

    public void moveDown(){
        nextMove = new Movement(new GridPoint2(Direction.DOWN.vector), Direction.DOWN.rotation);
    }

    public void moveLeft(){
        nextMove = new Movement(new GridPoint2(Direction.LEFT.vector), Direction.LEFT.rotation);
    }

    public void moveRight(){
        nextMove = new Movement(new GridPoint2(Direction.RIGHT.vector), Direction.RIGHT.rotation);
    }
}

