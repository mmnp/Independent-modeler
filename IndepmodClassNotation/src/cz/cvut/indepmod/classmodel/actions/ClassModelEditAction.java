package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultGraphCell;
import org.openide.windows.WindowManager;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 19:16:01
 * <p/>
 * This action will be used for editing of the classe vertices
 */
public class ClassModelEditAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Edit";
    private static final Logger LOG = Logger.getLogger(ClassModelEditAction.class.getName());
    private ClassModelGraph graph;

    public ClassModelEditAction(ClassModelGraph graph) {
        super(ACTION_NAME, null);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            DefaultGraphCell cell = (DefaultGraphCell) this.graph.getSelectionCell();
            ClassModel model = (ClassModel) cell.getUserObject();

            ClassModelEditClassDialog dialog = new ClassModelEditClassDialog(
                    WindowManager.getDefault().getMainWindow(),
                    graph,
                    cell,
                    model);

            //this.graph.getGraphLayoutCache().editCell(cell, map);
        } catch (NullPointerException ex) {
            LOG.severe("Edit Action was performed even is no cell was selected!");
        } catch (ClassCastException ex) {
            LOG.severe("Edit Action was performed on different vertex than class");
        }
    }
}
