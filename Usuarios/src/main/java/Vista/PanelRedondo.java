package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class PanelRedondo extends JPanel {
    int radio = 20;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Crear una forma redondeada con bordes redondeados
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radio, radio);

        // Dibujar el panel con bordes redondeados
        g2.setColor(getBackground());
        g2.fill(roundRect);
        g2.setColor(getForeground());
        g2.draw(roundRect);

        g2.dispose();
    }
}