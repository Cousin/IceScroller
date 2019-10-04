package net.blancodev.icescroller.render;

import java.awt.*;

public abstract class Renderable {

    private Image[] images;

    public Renderable(Image[] images) {
        this.images = images;
    }

    public abstract void render(Graphics2D graphics2D, int gameTick);

    public Image[] getImages() {
        return images;
    }
}
