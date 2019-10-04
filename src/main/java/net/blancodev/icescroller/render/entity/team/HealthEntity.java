package net.blancodev.icescroller.render.entity.team;

import net.blancodev.icescroller.IceScroller;
import net.blancodev.icescroller.render.Entity;
import net.blancodev.icescroller.render.EntityManager;

import java.awt.*;

// Entity which will heal player X hearts
public class HealthEntity extends Entity  {

    public HealthEntity(int x, int y) {
        super(new Image[] {
                IceScroller.loadFromResource("heart.png")
        }, "HealthEntity", false,0, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D, int gameTick) {

        PlayerEntity player = EntityManager.getEntityManager().getPlayerEntity();

        graphics2D.drawImage(getImages()[0], getX(), getY(), 25, 25, null);

        if (colliding(player)) {
            player.setHealth(player.getHealth() + 1);
            EntityManager.getEntityManager().removeEntity(this);
        }

        setY(getY() + 5);

        if (getY() >= 650) {
            EntityManager.getEntityManager().removeEntity(this);
        }

    }
}
