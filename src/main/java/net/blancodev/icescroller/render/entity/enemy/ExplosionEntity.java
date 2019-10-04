package net.blancodev.icescroller.render.entity.enemy;

import net.blancodev.icescroller.IceScroller;
import net.blancodev.icescroller.render.Entity;
import net.blancodev.icescroller.render.EntityManager;
import net.blancodev.icescroller.render.entity.team.PlayerEntity;

import java.awt.*;

// Entity which will blow up every surrounding team entity and destroy them, as well as doing X hearts
public class ExplosionEntity extends Entity {

    public ExplosionEntity(int x, int y) {
        super(new Image[] {
                IceScroller.loadFromResource("bomb.png")
        }, "ExplosionEntity", true,0, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D, int gameTick) {

        PlayerEntity player = EntityManager.getEntityManager().getPlayerEntity();

        graphics2D.drawImage(getImages()[0], getX(), getY(), 35, 35, null);

        if (colliding(player)) {
            player.setHealth(player.getHealth() - 2.5);
            IceScroller.SCORE = (int) (IceScroller.SCORE * 0.45);
            EntityManager.getEntityManager().removeEntity(this);
            for (Entity entity : EntityManager.getEntityManager().getRenderedEntities()) {
                if (!entity.isEnemy() && !(entity instanceof PlayerEntity)) {
                    EntityManager.getEntityManager().removeEntity(entity);
                }
            }
        }

        setY(getY() + 10);

        if (getY() >= 650) {
            EntityManager.getEntityManager().removeEntity(this);
        }

    }
}
