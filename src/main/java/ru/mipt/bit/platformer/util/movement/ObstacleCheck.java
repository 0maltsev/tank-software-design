package ru.mipt.bit.platformer.util.movement;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.objects.BaseObject;
import ru.mipt.bit.platformer.util.objects.Bullet;
import ru.mipt.bit.platformer.util.objects.Player;
import ru.mipt.bit.platformer.util.objects.Tree;
import ru.mipt.bit.platformer.util.observer.BulletAction;
import ru.mipt.bit.platformer.util.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObstacleCheck implements Observer {
    private final List<Player> tanks;
    private final List<Tree> trees;
    private final List<Bullet> bullets;

    private int height;
    private int width;

    public ObstacleCheck() {
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        bullets = new ArrayList<>();
        height = 0;
        width = 0;
    }

    public void addTank(Player tank) {
        tanks.add(tank);
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean noCollisionsForTank(GridPoint2 newCoordinates, Player tankToMove) {
        return  noObstacleTankWithTank(newCoordinates, tankToMove) &&
                noObstacleTreeWithTank(newCoordinates, tankToMove) &&
                noObstacleWithWall(newCoordinates);
    }

    private boolean noObstacleWithWall(GridPoint2 newCoordinates) {
        return newCoordinates.x >= 0 && newCoordinates.x < width &&
                newCoordinates.y >= 0 && newCoordinates.y < height;
    }

    private boolean noObstacleTreeWithTank(GridPoint2 newCoordinates, Player tankToMove) {
        for (Tree tree : trees) {
            if (!tankToMove.isTankMovementPossible(tree.getCoordinates(), newCoordinates))
                return false;
        }
        return true;
    }

    private boolean noObstacleTankWithTank(GridPoint2 newCoordinates, Player tankToMove) {
        for (Player tank : tanks) {
            if (tank.equals(tankToMove)) {
                continue;
            }
            if     (!tankToMove.isTankMovementPossible(tank.getCoordinates(), newCoordinates) ||
                    !tankToMove.isTankMovementPossible(tank.getDestination(), newCoordinates))

                return false;
        }
        return true;
    }

    public boolean noObstacleForBullet(GridPoint2 newCoordinates, Bullet bullet) {
        if (!noObstacleWithWall(newCoordinates)) {
            bullet.setNotExistent();
            return false;
        }

        return  noObstacleBulletWithBullet(newCoordinates, bullet) &&
                noObstacleBulletWithTank(newCoordinates, bullet) &&
                noObstacleBulletWithTree(newCoordinates, bullet);
    }

    private boolean noObstacleBulletWithBullet(GridPoint2 newCoordinates, Bullet bulletToMove) {
        for (Bullet bullet : bullets) {
            if (bullet.equals(bulletToMove)) {
                continue;
            }
            if (!bulletToMove.isBulletMovementPossible(bullet.getCoordinates(), newCoordinates) ||
                    !bulletToMove.isBulletMovementPossible(bullet.getDestinationCoordinates(), newCoordinates)) {
                bullet.setNotExistent();
                bulletToMove.setNotExistent();
                return false;
            }
        }
        return true;
    }

    private boolean noObstacleBulletWithTank(GridPoint2 newCoordinates, Bullet bullet) {
        for (Player tank : tanks) {
            if (!tank.equals(bullet.getTank()) && (!bullet.isBulletMovementPossible(tank.getCoordinates(), newCoordinates) ||
                    !bullet.isBulletMovementPossible(tank.getDestination(), newCoordinates)) ) {

                bullet.setNotExistent();

                tank.takeDamage(bullet);
                return false;
            }
        }
        return true;
    }

    private boolean noObstacleBulletWithTree(GridPoint2 newCoordinates, Bullet bullet) {

        for (Tree tree : trees) {
            if (!bullet.isBulletMovementPossible(tree.getCoordinates(), newCoordinates)) {
                bullet.setNotExistent();
                return false;
            }
        }
        return true;
    }
    

    @Override
    public void update(BulletAction event, BaseObject gameObject, int id) {
        switch(event) {
            case RemoveTank:
                tanks.remove((Player) gameObject);
                break;
            case RemoveBullet:
                bullets.remove((Bullet) gameObject);
                break;
            case AddBullet:
                bullets.add((Bullet) gameObject);
                break;
        }
    }
}
