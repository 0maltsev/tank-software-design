package ru.mipt.bit.platformer.AI;

import ru.mipt.bit.platformer.AI.Action;
import ru.mipt.bit.platformer.AI.state.movable.Actor;

public class Recommendation {
    private final Actor actor;
    private final Action action;

    public Recommendation(Actor actor, Action action) {
        this.actor = actor;
        this.action = action;
    }

    public Actor getActor() {
        return actor;
    }

    public Action getAction() {
        return action;
    }
}
