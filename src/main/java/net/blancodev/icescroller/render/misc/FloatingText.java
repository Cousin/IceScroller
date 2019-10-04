package net.blancodev.icescroller.render.misc;

import net.blancodev.icescroller.render.Renderable;

import java.awt.*;

public class FloatingText extends Renderable {

    private String text;
    private Font font;
    private Color color;
    private int x;
    private int centerY;
    private double y;

    private double amount;
    private double increment;

    private boolean add;

    public FloatingText(String text, Font font, Color color, int x, int y, int amount, double increment) {
        super(new Image[0]);
        this.text = text;
        this.font = font;
        this.color = color;
        this.x = x;
        this.centerY = y;
        this.y = y;
        this.amount = amount;
        this.increment = increment;
        this.add = true;
    }

    @Override
    public void render(Graphics2D graphics2D, int gameTick) {

        Color oldColor = graphics2D.getColor();
        Font oldFont = graphics2D.getFont();

        graphics2D.setColor(color);
        graphics2D.setFont(font);

        y = (add ? y + increment : y - increment);

        if (add) {
            if (y - centerY >= amount) {
                add = false;
            }
        } else {
            if (y - centerY <= -amount) {
                add = true;
            }
        }

        graphics2D.drawString(text, x, (int) y);

        graphics2D.setColor(oldColor);
        graphics2D.setFont(oldFont);

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
