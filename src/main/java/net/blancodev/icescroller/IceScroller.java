package net.blancodev.icescroller;

import net.blancodev.icescroller.render.EntityManager;
import net.blancodev.icescroller.render.entity.team.PlayerEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IceScroller {

    public static final String VERSION = "1.0";

    public static int SCORE = 0;

    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame jFrame = new JFrame("IceScroller v" + VERSION + " - BlancoDev");
        jFrame.setSize(500, 650);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        JPanel mainPanel = new JPanel();

        jFrame.add(mainPanel);

        EntityManager.getEntityManager().addEntity(new PlayerEntity(jFrame.getWidth() / 2 - 25, 550));

        jFrame.addKeyListener(new ICKeyListener());

        new Thread(new GameLoop(jFrame, mainPanel)).start();

    }

    public static Image loadFromResource(String fileName) {
        try {
            return ImageIO.read(IceScroller.class.getResource("/" + fileName));
        } catch (IOException e) {
            return null;
        }
    }

}
