/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor.cell.component;

import cz.cvut.indepmod.sequencemodel.editor.cell.model.SequenceModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * @author hegladan <hegladan@fel.cvut.cz>
 */

public class LifelineComponent extends JComponent {
    private static final Font CLASS_NAME_FONT = new Font("Serif", Font.BOLD, 15);

    private final SequenceModel model;

    public LifelineComponent(SequenceModel model){
        this.model = model;
    }

    private void paintSequence(Graphics g){
        int width = 100;
        int height = 100;
        String lifelineName = "default";

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);

        g.setFont(CLASS_NAME_FONT);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(lifelineName, g);
        g.drawString(lifelineName, 10, 10);
    }

}
