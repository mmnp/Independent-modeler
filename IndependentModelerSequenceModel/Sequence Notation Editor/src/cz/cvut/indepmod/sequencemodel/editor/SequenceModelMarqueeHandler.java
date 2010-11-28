/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor;

import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.PortView;

/**
 *
 * @author hegladan <hegladan@fel.cvut.cz>
 */
public class SequenceModelMarqueeHandler extends BasicMarqueeHandler{

    private static final Logger LOG = Logger.getLogger(SequenceModelMarqueeHandler.class.getName());
    
    private final SequenceModelGraph graph;
    private final ToolChooserModel selectedTool;

    private PortView actualPort;
    private PortView startPort;
    private Point2D actualPoint;
    private Point2D startPoint;
    
    public SequenceModelMarqueeHandler(SequenceModelGraph graph, ToolChooserModel selectedTool){
        this.graph = graph;
        this.selectedTool = selectedTool;
    }

    @Override
    public void mousePressed(final MouseEvent e){
        if (SwingUtilities.isLeftMouseButton(e) && isSelectedLifeline()){
            this.graph.insertCell(e.getPoint());
        } else if(this.actualPort != null && this.graph.isPortsVisible()){
            this.startPort = this.actualPort;
            this.startPoint = this.startPort.getLocation();
        }else{
            super.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if(this.startPoint != null && this.graph.isPortsVisible()){
            this.printTempLine(Color.black);

            this.actualPort = this.graph.getPortViewAt(e.getPoint().getX(), e.getPoint().getY());
            this.actualPoint = this.actualPort != null ? this.actualPort.getLocation() : e.getPoint();

            this.printTempLine(Color.black);
        }else{
            super.mouseDragged(e);
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

    public boolean isSelectedLifeline(){
        ToolChooserModel.Tool tool;
        try {
            tool = this.selectedTool.getSelectedTool();
        } catch (ClassCastException ex) {
            return false;
        }

        switch (tool) {
            case TOOL_LIFELINE:
                return true;
            default:
                return false;
        }
    }

    private void printTempLine(Color color) {
            if (this.startPoint != null && this.actualPoint != null) {
            Graphics2D g = (Graphics2D) this.graph.getGraphics();
            g.setColor(color);
            g.setXORMode(this.graph.getBackground());

            g.drawLine(
                    (int) this.startPoint.getX(),
                    (int) this.startPoint.getY(),
                    (int) this.actualPoint.getX(),
                    (int) this.actualPoint.getY()
            );
        }
    }
}
