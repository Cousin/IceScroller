package net.blancodev.icescroller;

import net.blancodev.icescroller.render.Entity;
import net.blancodev.icescroller.render.EntityManager;
import net.blancodev.icescroller.render.entity.enemy.DamageEntity;
import net.blancodev.icescroller.render.entity.enemy.ExplosionEntity;
import net.blancodev.icescroller.render.entity.team.HealthEntity;
import net.blancodev.icescroller.render.entity.team.PlayerEntity;
import net.blancodev.icescroller.render.misc.FloatingText;
import net.blancodev.icescroller.render.misc.HealthBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class GameLoop implements Runnable {

    private JFrame jFrame;
    private JPanel jPanel;
    private int gameTick;

    public GameLoop(JFrame jFrame, JPanel jPanel) {
        this.jFrame = jFrame;
        this.jPanel = jPanel;
        this.gameTick = 0;
    }

    @Override
    public void run() {

        boolean textFloatUp = true;

        BufferedImage bufferedImage = new BufferedImage(500, 650, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

        PlayerEntity playerEntity = new PlayerEntity(jFrame.getWidth() / 2 - 25, 550);
        HealthBar healthBar = new HealthBar();

        graphics2D.setFont(new Font("Arial", Font.BOLD, 36));
        FloatingText iceScrollerMainMenu = new FloatingText(
                "IceScroller", new Font("Arial", Font.BOLD, 36), Color.CYAN,
                (jFrame.getWidth() - graphics2D.getFontMetrics().stringWidth("IceScroller")) / 2, 100, 35, 3
        );

        FloatingText scoreText = new FloatingText("Score: " + IceScroller.SCORE,
                new Font("Arial", Font.PLAIN, 20), Color.BLACK, 350, 30, 5, 0.5);

        graphics2D.setFont(new Font("Arial", Font.BOLD, 24));
        FloatingText pressEnterToStart = new FloatingText(
                "Press Space to Start", new Font("Arial", Font.BOLD, 24), Color.BLACK,
                (jFrame.getWidth() - graphics2D.getFontMetrics().stringWidth("Press Space to Start")) / 2, 450, 20, 0.5
        );

        while (true) {

            bufferedImage = new BufferedImage(500, 650, BufferedImage.TYPE_INT_RGB);
            graphics2D = (Graphics2D) bufferedImage.getGraphics();

            Toolkit.getDefaultToolkit().sync();

            //TODO: replace background
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(0,0, 500, 650);

            if (GameState.GAME_STATE == GameState.START) {

                iceScrollerMainMenu.render(graphics2D, gameTick);
                pressEnterToStart.render(graphics2D, gameTick);

            } else if (GameState.GAME_STATE == GameState.DEAD) {



            } else if (GameState.GAME_STATE == GameState.GAME) {
                if (gameTick % 45 == 0) {

                    IceScroller.SCORE += 5;

                    double random = Math.random();

                    if (random >= 0.75) {
                        EntityManager.getEntityManager().addEntity(
                                new ExplosionEntity(20 + (ThreadLocalRandom.current().nextInt(400)), 40)
                        );
                    } else if (random >= 0.45) {
                        EntityManager.getEntityManager().addEntity(
                                new HealthEntity(20 + (ThreadLocalRandom.current().nextInt(400)), 40)
                        );
                    } else {

                        EntityManager.getEntityManager().addEntity(
                                new DamageEntity(20 + (ThreadLocalRandom.current().nextInt(400)), 40)
                        );

                    }

                }

                for (Entity entity : EntityManager.getEntityManager().getRenderedEntities()) {
                    entity.render(graphics2D, gameTick);
                }

                scoreText.setText("Score: " + IceScroller.SCORE);

                scoreText.render(graphics2D, gameTick);
                healthBar.render(graphics2D, gameTick);
                IceScroller.SCORE++;

            }

            jPanel.getGraphics().drawImage(bufferedImage, 0, 0, jFrame);

            gameTick ++;

            try {
                // 25 fps
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
