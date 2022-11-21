package ru.mipt.bit.platformer.util.actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.AI.Command;
import ru.mipt.bit.platformer.util.enums.Direction;
import ru.mipt.bit.platformer.util.objects.Bullet;
import ru.mipt.bit.platformer.util.objects.Level;
import ru.mipt.bit.platformer.util.objects.Player;

import java.util.Date;

public class Shooting implements Command {
    private final Player tank;
    private final Level logicLevel;
    public Shooting(Player tank, Level logicLevel){
        this.tank = tank;
        this.logicLevel = logicLevel;
    }
    @Override
    public void execute(){
        tank.shoot();
        if (tank.canShoot()) {

            GridPoint2 bulletCoords = getNextCoords();
            Bullet bullet = new Bullet(bulletCoords, tank.getRotation(), tank);

        }
        tank.setLastTimeShooting(new Date().getTime());
    }

    private GridPoint2 getNextCoords() {
        GridPoint2 destCoords;

        GridPoint2 coords = tank.getCoordinates();
        float rotation = tank.getRotation();

        if (rotation == Direction.UP.rotation)
            destCoords = coords.add(Direction.UP.vector);
        else if (rotation == Direction.RIGHT.rotation)
            destCoords = coords.add(Direction.RIGHT.vector);
        else if (rotation == Direction.DOWN.rotation)
            destCoords = coords.add(Direction.DOWN.vector);
        else
            destCoords = coords.add(Direction.LEFT.vector);

        return destCoords;
    }
}
