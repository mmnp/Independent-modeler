package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;

public class NoteRenderer extends VertexRenderer {

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,150);
    }

    @Override
    public void paint(Graphics g) {
        int b = borderWidth;
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        boolean tmp = selected;
        if (super.isOpaque()) {
            g.setColor(super.getBackground());
            if (gradientColor != null && !preview) {
                setOpaque(false);
                g2.setPaint(new GradientPaint(0, 0, getBackground(), getWidth(), getHeight(), gradientColor, true));
            }
            g.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            g.drawLine(0, 0, d.width - (int) (b * 1.5)-10, 0);
            g.drawLine(d.width - (int) (b * 1.5)-10, 0, d.width - (int) (b * 1.5)-10, 10);
            g.drawLine(d.width - (int) (b * 1.5)-10, 0, d.width - (int) (b * 1.5), 10);
            g.drawLine(d.width - (int) (b * 1.5)-10, 10, d.width - (int) (b * 1.5), 10);
            g.drawLine(d.width - (int) (b * 1.5), 10, d.width - (int) (b * 1.5), d.height - (int) (b * 1.5));
            g.drawLine(d.width - (int) (b * 1.5), d.height - (int) (b * 1.5), 0, d.height - (int) (b * 1.5));
            g.drawLine(0, d.height - (int) (b * 1.5), 0, 0);
        }
        try {
            setBorder(null);
            setOpaque(false);
            selected = false;
            super.paint(g);
        } finally {
            selected = tmp;
        }
        if (bordercolor != null) {
            g.setColor(bordercolor);
            g2.setStroke(new BasicStroke(b));
            g.drawRect(b / 2, b / 2, d.width - (int) (b * 1.5), d.height - (int) (b * 1.5));
        }
        if (selected) {
            g2.setStroke(GraphConstants.SELECTION_STROKE);
            g.setColor(highlightColor);
            g.drawRect(b / 2, b / 2, d.width - (int) (b * 1.5), d.height - (int) (b * 1.5));
        }
    }
}
