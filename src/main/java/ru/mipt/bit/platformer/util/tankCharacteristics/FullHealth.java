package ru.mipt.bit.platformer.util.tankCharacteristics;

import ru.mipt.bit.platformer.util.objects.Player;

import java.util.Date;

public class FullHealth implements HealthStatus{
    private final Player tank;

    public FullHealth(Player tank) {
        this.tank = tank;
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
