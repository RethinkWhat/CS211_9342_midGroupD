package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJButton extends JButton {
    private Shape shape;
    Resources resources = new Resources();
    public RoundJButton() {
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(resources.greyishBlack);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}