package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelDeleteAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelRedoAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelUndoAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.modelFactory.ClassModelDiagramModelFactory;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;

/**
 * This class represents a Workspace of class model notation. It contains JGraph.
 * @author Lucky
 */

public class ClassModelWorkspace extends TopComponent {

    public static final String DISPLAY_NAME = "Class Notation";

    private ClassModelGraph graph;
    private JPopupMenu popupMenu;
    private Map<String, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;

    private InstanceContent lookupContent = new InstanceContent();

    public ClassModelWorkspace() {
        this.actions = new HashMap<String, ClassModelAbstractAction>();
        this.popupMenu = new JPopupMenu();
        this.selectedTool = new ToolChooserModel();
        this.graph = new ClassModelGraph(this.actions, this.selectedTool);

        this.associateLookup(new AbstractLookup(this.lookupContent));
        this.lookupContent.add(this.selectedTool);
        
        this.graph.setMarqueeHandler(new ClassModelMarqueeHandler(this.graph, this.selectedTool, this.popupMenu));
        this.graph.setGraphLayoutCache(ClassModelDiagramModelFactory.getInstance().createEmptyDiagramModel().getLayoutCache()); //TODO: THIS SHOULD BE ADDED THROUGH CONSTRUCTOR

        this.initActions();
        this.initPopupMenu();
        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridLayout(1, 1));
        this.add(new JScrollPane(this.graph));
    }

    private void initActions() {
        this.actions.put(
                ClassModelDeleteAction.ACTION_NAME,
                new ClassModelDeleteAction(this.graph)
        );

        this.actions.put(
                ClassModelUndoAction.ACTION_NAME,
                new ClassModelUndoAction()
        );

        this.actions.put(
                ClassModelRedoAction.ACTION_NAME,
                new ClassModelRedoAction()
        );
    }

    private void initPopupMenu() {
        ClassModelAbstractAction deleteAction = this.actions.get(ClassModelDeleteAction.ACTION_NAME);
        deleteAction.setEnabled(true);

        ClassModelAbstractAction editAction = this.actions.get(ClassModelEditAction.ACTION_NAME);

        this.popupMenu.add(deleteAction);
        this.popupMenu.add(editAction);
    }
}
