/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor.cell;

import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

/**
 *
 * @author hegladan <hegladan@fel.cvut.cz>
 */
public class SequenceModelCellFactory {
    private static final Logger LOG = Logger.getLogger(SequenceModelCellFactory.class.getName());
    private static int counter = 0;
    private static int location = 50;

     public static DefaultGraphCell createCells(Point2D point, ToolChooserModel.Tool selectedTool) {
        DefaultGraphCell[] cells = null;
        DefaultEdge edge = new DefaultEdge();
        DefaultGraphCell parentCell;

        switch (selectedTool) {
            case TOOL_LIFELINE_NAME:
                cells = new DefaultGraphCell[3];
                cells[0] = new DefaultGraphCell(new String(":Lifeline" + ++counter));
                cells[1] = new DefaultGraphCell(new String(""));
                cells[2] = edge;
                break;
            default:
                LOG.severe("Unknown selected tool");
        }

        cells[0].add(new DefaultPort());
        cells[1].add(new DefaultPort());

        GraphConstants.setBounds(
                cells[0].getAttributes(),
                new Rectangle2D.Double(
                        location,
                        20,
                        //point.getX(),
                        //point.getY(),
                        60,
                        30
                )
        );

        GraphConstants.setBounds(
                cells[1].getAttributes(),
                new Rectangle2D.Double(
                        location+30,
                        100,
                        0,
                        0
                )
        );

        location+=100;

        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        //GraphConstants.setLineEnd(cells[2].getAttributes(), GraphConstants.PERMILLE);
        //GraphConstants.setEndFill(cells[2].getAttributes(), true);
        float [] fl = {5,5};
        GraphConstants.setDashPattern(edge.getAttributes(),fl);

        GraphConstants.setGradientColor(cells[0].getAttributes(), Color.YELLOW);
        GraphConstants.setOpaque(cells[0].getAttributes(), true);
        GraphConstants.setSizeable(cells[0].getAttributes(), false);
        //GraphConstants.setResize(cell.getAttributes(), true);
        parentCell = new DefaultGraphCell(new String("Lifeline"),null,cells);

        return parentCell;
    }
}
