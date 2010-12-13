/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;

/**
 *
 * @author Petr Vales
 */
public class DataRenderer extends VertexRenderer {

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
            this.drawProcess(g, d, b);
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
            this.drawProcess(g, d, b);
        }
        if (selected) {
            g2.setStroke(GraphConstants.SELECTION_STROKE);
            g.setColor(highlightColor);
            this.drawProcess(g, d, b);
        }
    }

    private void drawProcess(Graphics g, Dimension d, int b) {
        g.drawLine(d.width/2, 0, d.width, d.height / 8);
        g.drawLine(d.width, d.height / 8, d.width, d.height);
        g.drawLine(d.width, d.height, 0, d.height);
        g.drawLine(0, d.height, 0, d.height / 8);
        g.drawLine(0, d.height / 8, d.width/2, 0);
    }
}
