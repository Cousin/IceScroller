package net.blancodev.icescroller.render;

import java.awt.*;

// Base class for Entities in IceScroller
public abstract class Entity extends Renderable {

    private Image[] images;
    private String name;
    private int id;

    private boolean enemy;

    private int x, y;

    public Entity(Image[] images, String name, boolean enemy, int id, int x, int y) {
        super(images);
        this.name = name;
        this.enemy = enemy;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public boolean colliding(Entity entity) {

        return x >= entity.x - 20 && x <= entity.x + 20
                && y >= entity.y - 20 && y <= entity.y + 20;

    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
