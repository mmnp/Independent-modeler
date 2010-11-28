/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor;

import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
import cz.cvut.indepmod.sequencemodel.api.ToolChooserModelListener;
import cz.cvut.indepmod.sequencemodel.editor.cell.SequenceModelCellFactory;
import java.awt.Point;
import java.util.logging.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;

/**
 *
 * @author hegladan <hegladan@fel.cvut.cz>
 */
public class SequenceModelGraph extends JGraph{

    private static final Logger LOG = Logger.getLogger(SequenceModelGraph.class.getName());

    private ToolChooserModel selectedTool;

    public SequenceModelGraph(ToolChooserModel selectedTool){
        this.selectedTool = selectedTool;

        this.initEventHandling();
    }

    public void insertCell(Point p) {
        LOG.fine("adding new cell");
        ToolChooserModel.Tool tool = this.selectedTool.getSelectedTool();
        DefaultGraphCell cells = SequenceModelCellFactory.createCells(p, tool);

        this.getGraphLayoutCache().insert(cells);
        this.selectedTool.setSelectedTool(ToolChooserModel.Tool.TOOL_INTERACTION);
    }

        private void initEventHandling() {
        this.selectedTool.addListener(new ToolChooserModelListener() {

            @Override
            public void selectedToolChanged(ToolChooserModel.Tool newTool) {
                boolean showPorts = false;
                ToolChooserModel.Tool tool = newTool;

                switch (tool) {
                    case TOOL_MESSAGE:
                        showPorts = true;
                }

                setPortsVisible(showPorts);
                setJumpToDefaultPort(showPorts);
            }
        });
    }

}
