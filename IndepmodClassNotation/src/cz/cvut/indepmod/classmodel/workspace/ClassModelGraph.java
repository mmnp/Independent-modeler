package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.ToolChooserModelListener;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Map;
import java.util.logging.Logger;

public class ClassModelGraph extends JGraph {

    private static final Logger LOG = Logger.getLogger(ClassModelGraph.class.getName());

    private Map<String, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;


    public ClassModelGraph(Map<String, ClassModelAbstractAction> actions, ToolChooserModel selectedTool) {
        this.actions = actions;
        this.selectedTool = selectedTool;

        this.initActions();
        this.initEventHandling();
    }

    private void initActions() {
        this.actions.put(
                ClassModelEditAction.ACTION_NAME,
                new ClassModelEditAction(this)
        );
    }


    private void initEventHandling() {
        this.selectedTool.addListener(new ToolChooserModelListener() {
            @Override
            public void selectedToolChanged(ToolChooserModel.Tool newTool) {
                boolean showPorts = false;
                ToolChooserModel.Tool tool = newTool;

                switch (tool) {
                    case TOOL_ADD_RELATION:
                        showPorts = true;
                }

                setPortsVisible(showPorts);
                setJumpToDefaultPort(showPorts);
            }
        });

        this.addGraphSelectionListener(new GraphSelectionListener() {
            @Override
            public void valueChanged(GraphSelectionEvent graphSelectionEvent) {
                actions.get(ClassModelEditAction.ACTION_NAME).setEnabled(getSelectionCell() != null);
            }
        });
    }

    public void insertCell(Point p) {
        LOG.fine("adding new cell");
        ToolChooserModel.Tool tool = this.selectedTool.getSelectedTool();
        DefaultGraphCell cell = ClassModelCellFactory.createCell(p, tool);

        this.getGraphLayoutCache().insert(cell);
        this.selectedTool.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
    }

}
