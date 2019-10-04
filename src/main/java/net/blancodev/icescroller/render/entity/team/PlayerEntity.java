package net.blancodev.icescroller.render.entity.team;

import net.blancodev.icescroller.IceScroller;
import net.blancodev.icescroller.render.Entity;

import java.awt.*;

// Main player entity
public class PlayerEntity extends Entity {

    private int runCounter;
    private boolean running;

    private double leftAcceleration;
    private double rightAcceleration;

    private double health;

    public PlayerEntity(int x, int y) {
        super(new Image[] {
                IceScroller.loadFromResource("playerstill.png"),
                IceScroller.loadFromResource("playerleftrun.png"),
                IceScroller.loadFromResource("playerrightrun.png")
        }, "PlayerEntity", false,0, x, y);

        runCounter = 0;
        running = true;

        leftAcceleration = 0;
        rightAcceleration = 0;

        health = 5;
    }

    @Override
    public void render(Graphics2D graphics2D, int gameTick) {

        Image toRender;

        runCounter++;

        if (runCounter >= 20) {
            runCounter = 0;
        }

        if (!running) {
            toRender = getImages()[0];
        } else {

            if (runCounter >= 10) {
                toRender = getImages()[2];
                System.out.println("2");
            } else {
                toRender = getImages()[1];
                System.out.println("1");
            }

        }

        if (leftAcceleration > 0) {
            setX(getX() - (int) (leftAcceleration));
        } else if (rightAcceleration > 0) {
            setX(getX() + (int) (rightAcceleration));
        }

        if (getX() <= 0) {
            leftAcceleration = 0;
            rightAcceleration = 15;
            health-=0.3;
        } else if (getX() >= 450) {
            rightAcceleration = 0;
            leftAcceleration = 15;
            health-=0.3;
        }

        if (rightAcceleration > 8) {
            rightAcceleration -= 0.5;
        } else if (leftAcceleration > 8) {
            leftAcceleration -= 0.5;
        }

        graphics2D.drawImage(toRender, getX(), getY(), 50, 50, null);

    }

    public void moveLeft() {
        rightAcceleration = Math.max(0, rightAcceleration - 0.5);
        if (rightAcceleration == 0) {
            leftAcceleration = Math.min(8, leftAcceleration + 1);
        }
    }

    public void moveRight() {
        leftAcceleration = Math.max(0, leftAcceleration - 0.5);
        if (leftAcceleration == 0) {
            rightAcceleration = Math.min(8, rightAcceleration + 1);
        }
    }

    public int getRunCounter() {
        return runCounter;
    }

    public boolean isRunning() {
        return running;
    }

    public double getLeftAcceleration() {
        return leftAcceleration;
    }

    public double getRightAcceleration() {
        return rightAcceleration;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = Math.min(5, health);
    }
}
