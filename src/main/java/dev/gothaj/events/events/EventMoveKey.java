package dev.gothaj.events.events;

import dev.gothaj.events.Event;

public class EventMoveKey extends Event {
        boolean forward;
        boolean backwards;
        boolean left;
        boolean right;
        boolean jump;
        boolean sneak;

        public boolean isForward() {
            return this.forward;
        }

        public boolean isBackwards() {
            return this.backwards;
        }

        public boolean isLeft() {
            return this.left;
        }

        public boolean isRight() {
            return this.right;
        }

        public boolean isJump() {
            return this.jump;
        }

        public boolean isSneak() {
            return this.sneak;
        }

        public void setForward(boolean forward) {
            this.forward = forward;
        }

        public void setBackwards(boolean backwards) {
            this.backwards = backwards;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public void setJump(boolean jump) {
            this.jump = jump;
        }

        public void setSneak(boolean sneak) {
            this.sneak = sneak;
        }

        public EventMoveKey(boolean forward, boolean backwards, boolean left, boolean right, boolean jump, boolean sneak) {
            this.forward = forward;
            this.backwards = backwards;
            this.left = left;
            this.right = right;
            this.jump = jump;
            this.sneak = sneak;
        }
}
