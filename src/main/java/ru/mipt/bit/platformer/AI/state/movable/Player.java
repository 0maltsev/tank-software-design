package ru.mipt.bit.platformer.AI.state.movable;

import ru.mipt.bit.platformer.AI.state.movable.Actor;
import ru.mipt.bit.platformer.AI.state.movable.Orientation;

public class Player  extends Actor {

    private Player(Object source, int x, int y, int destX, int destY, Orientation orientation) {
        super(source, x, y, destX, destY, orientation);
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {

        private Object source;
        private int x;
        private int y;
        private int destX;
        private int destY;
        private Orientation orientation;

        public PlayerBuilder source(Object source) {
            this.source = source;
            return this;
        }

        public PlayerBuilder x(int x) {
            this.x = x;
            return this;
        }

        public PlayerBuilder y(int y) {
            this.y = y;
            return this;
        }

        public PlayerBuilder destX(int destX) {
            this.destX = destX;
            return this;
        }

        public PlayerBuilder destY(int destY) {
            this.destY = destY;
            return this;
        }

        public PlayerBuilder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Player build() {
            return new Player(source, x, y, destX, destY, orientation);
        }
    }
}
