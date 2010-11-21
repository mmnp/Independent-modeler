/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor;

import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.jgraph.graph.BasicMarqueeHandler;

/**
 *
 * @author hegladan <hegladan@fel.cvut.cz>
 */
public class SequenceModelMarqueeHandler extends BasicMarqueeHandler{

    private static final Logger LOG = Logger.getLogger(SequenceModelMarqueeHandler.class.getName());
    
    private final SequenceModelGraph graph;
    private final ToolChooserModel selectedTool;
    
    public SequenceModelMarqueeHandler(SequenceModelGraph graph, ToolChooserModel selectedTool){
        this.graph = graph;
        this.selectedTool = selectedTool;
    }

    @Override
    public void mousePressed(final MouseEvent e){
        if (SwingUtilities.isLeftMouseButton(e) && isSelectedLifeline()){
            this.graph.insertCell(e.getPoint());
        } else {
            super.mousePressed(e);
        }
    }

    public boolean isSelectedLifeline(){
        ToolChooserModel.Tool tool;
        try {
            tool = this.selectedTool.getSelectedTool();
        } catch (ClassCastException ex) {
            return false;
        }

        switch (tool) {
            case TOOL_LIFELINE_NAME:
                return true;
            default:
                return false;
        }
    }
}
