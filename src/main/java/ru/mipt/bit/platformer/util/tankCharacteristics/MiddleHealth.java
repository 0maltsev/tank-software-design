package ru.mipt.bit.platformer.util.tankCharacteristics;

import ru.mipt.bit.platformer.util.objects.Player;

import java.util.Date;

public class MiddleHealth implements HealthStatus{
    private final Player tank;

    public MiddleHealth(Player tank) {
        this.tank = tank;
        this.tank.setMovementSpeed(tank.getMovementSpeed() * 2f);
    }

    @Override
    public boolean canShoot() {
        long time = new Date().getTime();
        long delta = time - tank.getLastTimeShooting();
        if (delta < 500)
            return false;
        tank.setLastTimeShooting(time);
        return true;
    }
}
