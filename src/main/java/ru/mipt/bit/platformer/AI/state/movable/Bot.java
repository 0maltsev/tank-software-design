package ru.mipt.bit.platformer.AI.state.movable;

import ru.mipt.bit.platformer.AI.state.movable.Actor;
import ru.mipt.bit.platformer.AI.state.movable.Orientation;

public class Bot extends Actor {
    private Bot(Object source, int x, int y, int destX, int destY, Orientation orientation) {
        super(source, x, y, destX, destY, orientation);
    }

    public static Bot.BotBuilder builder() {
        return new Bot.BotBuilder();
    }

    public static class BotBuilder {

        private Object source;
        private int x;
        private int y;
        private int destX;
        private int destY;
        private Orientation orientation;

        public Bot.BotBuilder source(Object source) {
            this.source = source;
            return this;
        }

        public Bot.BotBuilder x(int x) {
            this.x = x;
            return this;
        }

        public Bot.BotBuilder y(int y) {
            this.y = y;
            return this;
        }

        public Bot.BotBuilder destX(int destX) {
            this.destX = destX;
            return this;
        }

        public Bot.BotBuilder destY(int destY) {
            this.destY = destY;
            return this;
        }

        public Bot.BotBuilder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Bot build() {
            return new Bot(source, x, y, destX, destY, orientation);
        }
    }
}
