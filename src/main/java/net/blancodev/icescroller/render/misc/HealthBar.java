package net.blancodev.icescroller.render.misc;

import net.blancodev.icescroller.IceScroller;
import net.blancodev.icescroller.render.EntityManager;
import net.blancodev.icescroller.render.Renderable;
import net.blancodev.icescroller.render.entity.team.PlayerEntity;

import java.awt.*;

public class HealthBar extends Renderable {

    public HealthBar() {
        super(new Image[] {
                IceScroller.loadFromResource("heart.png"),
                IceScroller.loadFromResource("emptyheart.png"),
        });
    }

    @Override
    public void render(Graphics2D graphics2D, int gameTick) {

        PlayerEntity player = EntityManager.getEntityManager().getPlayerEntity();

        int startX = 10;
        int y = 10;

        for (int i = 0; i <= 5; i++) {
            Image toDraw = player.getHealth() >= i ? getImages()[0] : getImages()[1];
            graphics2D.drawImage(toDraw, startX, y, 20, 20, null);
            startX += 30;
        }

    }
}
