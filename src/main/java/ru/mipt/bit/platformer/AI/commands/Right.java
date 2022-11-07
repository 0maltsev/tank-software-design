package ru.mipt.bit.platformer.AI.commands;

import ru.mipt.bit.platformer.AI.Command;
import ru.mipt.bit.platformer.util.objects.Player;

public class Right implements Command {
    private final Player tank;

    public Right(Player tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.moveRight();
    }
}
