package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.ToolChooserModelListener;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.jgraph.graph.CellView;

public class ClassModelGraph extends JGraph {

    private static final Logger LOG = Logger.getLogger(ClassModelGraph.class.getName());

    private Map<String, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;

    private Set<TypeModel> staticModels;


    public ClassModelGraph(Map<String, ClassModelAbstractAction> actions, ToolChooserModel selectedTool) {
        this.actions = actions;
        this.selectedTool = selectedTool;

        this.initActions();
        this.initEventHandling();
        this.initStaticTypes();
    }

    //TODO - this could be saved (and updated when model id changed)
    public Collection<TypeModel> getAllTypes() {
        Collection<TypeModel> res = new LinkedList<TypeModel>(this.getAllClasses());
//        CellView[] cw = this.getGraphLayoutCache().getCellViews();
//        for (int i = 0; i < cw.length; i++) {
//            DefaultGraphCell cell = (DefaultGraphCell)cw[i].getCell();
//            Object userObject = cell.getUserObject();
//            if (userObject instanceof ClassModel) {
//                res.add((TypeModel) userObject);
//            }
//        }

        res.addAll(this.staticModels);
        return res;
    }

    /**
     * Returns collection of all classes that are in the Graph
     * @return Colection of all classes
     */
    public Collection<ClassModel> getAllClasses() {
        Collection<ClassModel> res = new LinkedList<ClassModel>();
        CellView[] cw = this.getGraphLayoutCache().getCellViews();
        for (int i = 0; i < cw.length; i++) {
            DefaultGraphCell cell = (DefaultGraphCell)cw[i].getCell();
            Object userObject = cell.getUserObject();
            if (userObject instanceof ClassModel) {
                res.add((ClassModel) userObject);
            }
        }
        return res;
    }

    public void insertCell(Point p) {
        LOG.fine("adding new cell");
        ToolChooserModel.Tool tool = this.selectedTool.getSelectedTool();
        DefaultGraphCell cell = ClassModelCellFactory.createCell(p, tool);

        this.getGraphLayoutCache().insert(cell);
        this.selectedTool.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
    }

    /**
     * TODO - this will be loaded from a XML. Only temporary
     */
    private void initStaticTypes() {
        this.staticModels = new HashSet<TypeModel>();
        this.staticModels.add(new TypeModel("String"));
        this.staticModels.add(new TypeModel("int"));
        this.staticModels.add(new TypeModel("char"));
        this.staticModels.add(new TypeModel("boolean"));
        this.staticModels.add(new TypeModel("long"));
        this.staticModels.add(new TypeModel("double"));
        this.staticModels.add(new TypeModel("float"));
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

}
