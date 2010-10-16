package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;

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

    private static final Logger LOG = Logger.getLogger(ClassModelEditAction.class);

    private ClassModelGraph graph;

    public ClassModelEditAction(ClassModelGraph graph) {
        super(ACTION_NAME, null);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        try {
//            DefaultGraphCell cell = (DefaultGraphCell) this.graph.getSelectionCell();
//            ClassModel model = (ClassModel) cell.getUserObject();
//
//            ClassModelEditClassDialogView dialog = new ClassModelEditClassDialogView(ModelerSession.getFrame());
//            dialog.setVisible(true);
//
//            //this.graph.getGraphLayoutCache().editCell(cell, map);
//        } catch (NullPointerException ex) {
//            LOG.error("Edit Action was performed even is no cell was selected!");
//        } catch (ClassCastException ex) {
//            LOG.error("Edit Action was performed on different vertex than class");
//        }
    }
}
