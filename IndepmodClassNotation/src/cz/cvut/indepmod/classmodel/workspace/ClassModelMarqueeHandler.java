package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.PortView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.logging.Logger;

public class ClassModelMarqueeHandler extends BasicMarqueeHandler {

    private static final Logger LOG = Logger.getLogger(ClassModelMarqueeHandler.class.getName());

    private final ClassModelGraph graph;
    private final ToolChooserModel selectedToolModel;
    private final JPopupMenu popupMenu;

    private PortView actualPort;
    private Point2D actualPoint;
    private PortView startingPort;
    private Point2D startingPoint;

    public ClassModelMarqueeHandler(ClassModelGraph graph,
                                    ToolChooserModel selectedToolModel,
                                    JPopupMenu popupMenu) {
        this.graph = graph;
        this.selectedToolModel = selectedToolModel;
        this.popupMenu = popupMenu;

        this.actualPort = null;
        this.actualPoint = null;
        this.startingPort = null;
        this.startingPoint = null;
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.popupMenu.show(this.graph, e.getX(), e.getY());
        } else if (this.addAction()) {
            this.graph.insertCell(e.getPoint());
        } else if (this.actualPort != null && this.graph.isPortsVisible()) {
            this.startingPort = this.actualPort;
            this.startingPoint = this.startingPort.getLocation();
        } else {
            super.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.startingPort != null && this.graph.isPortsVisible()) {
            LOG.fine("mouseDraged");
            this.printTempLine(Color.black, this.graph.getBackground());

            this.actualPort = this.graph.getPortViewAt(mouseEvent.getPoint().getX(), mouseEvent.getPoint().getY());
            this.actualPoint = this.actualPort != null ? this.actualPort.getLocation() : mouseEvent.getPoint();

            this.printTempLine(Color.black, this.graph.getBackground());
        } else {
            super.mouseDragged(mouseEvent);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (this.startingPort != null && this.graph.isPortsVisible()) {
            this.printTempLine(Color.black, this.graph.getBackground());

            if (this.actualPort != null && !this.actualPort.equals(this.startingPort)) {
                ClassModelRelation edge = new ClassModelRelation(new RelationModel(RelationType.RELATION));
                edge.setSource(this.startingPort.getCell());
                edge.setTarget(this.actualPort.getCell());

                GraphConstants.setEndFill(edge.getAttributes(), true);
                GraphConstants.setLineStyle(edge.getAttributes(), GraphConstants.STYLE_ORTHOGONAL);
                GraphConstants.setLabelAlongEdge(edge.getAttributes(), false);
                GraphConstants.setEditable(edge.getAttributes(), true);
                GraphConstants.setMoveable(edge.getAttributes(), true);
                GraphConstants.setDisconnectable(edge.getAttributes(), false);

                this.graph.getGraphLayoutCache().insert(edge);
            }

            this.actualPort = null;
            this.actualPoint = null;
            this.startingPort = null;
            this.startingPoint = null;
            this.selectedToolModel.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
        } else {
            super.mouseReleased(mouseEvent);
        }
    }

    @Override
    public boolean isForceMarqueeEvent(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent) && !mouseEvent.isShiftDown()) {
            return true;
        }

        this.actualPort = this.graph.getPortViewAt(mouseEvent.getPoint().getX(), mouseEvent.getPoint().getY());
        return (this.actualPort != null && this.graph.isPortsVisible()) || super.isForceMarqueeEvent(mouseEvent);
    }

    public boolean addAction() {
        ToolChooserModel.Tool tool;
        try {
            tool = this.selectedToolModel.getSelectedTool();
        } catch (ClassCastException ex) {
            return false;
        }

        switch (tool) {
            case TOOL_ADD_CLASS:
                return true;
            default:
                return false;
        }
    }

    //TODO - newColor and oldColor are the same in all calls

    /**
     * This method prints temporary line when user want add an connection between vertices. Line is printed in XOR mode,
     * so if newColor pixel is printed on the pixel with the same color (newColor color), the oldColor pixel is
     * printed (like XOR operation)
     *
     * @param newColor color which will be used if repainted pixel has another color
     * @param oldColor color which will be used if repainted pixel has equal color to the newColor
     */
    private void printTempLine(Color newColor, Color oldColor) {
        if (this.startingPoint != null && this.actualPoint != null) {
            Graphics2D g = (Graphics2D) this.graph.getGraphics();
            g.setColor(newColor);
            g.setXORMode(oldColor);

            g.drawLine(
                    (int) this.startingPoint.getX(),
                    (int) this.startingPoint.getY(),
                    (int) this.actualPoint.getX(),
                    (int) this.actualPoint.getY()
            );
        }
    }

}
