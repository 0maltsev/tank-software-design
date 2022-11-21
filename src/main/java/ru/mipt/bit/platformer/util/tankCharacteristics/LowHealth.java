package ru.mipt.bit.platformer.util.tankCharacteristics;

import ru.mipt.bit.platformer.util.objects.Player;

public class LowHealth implements HealthStatus{
    private final Player tank;

    public LowHealth(Player tank) {
        this.tank = tank;
        this.tank.setMovementSpeed(tank.getMovementSpeed() * 1.5f);
    }

    @Override
    public boolean canShoot() {
        return false;
    }
}
