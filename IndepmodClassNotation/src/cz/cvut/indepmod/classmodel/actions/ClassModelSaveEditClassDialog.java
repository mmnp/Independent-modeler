/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 23.10.2010
 * Time: 11:32:59
 * @author Lucky
 */
public class ClassModelSaveEditClassDialog extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Save";
    
    private static final Logger LOG = Logger.getLogger(ClassModelSaveEditClassDialog.class.getName());

    private ClassModel model;
    private DefaultGraphCell cell;
    private ClassModelGraph graph;
    private ClassModelEditClassDialog dialog;

    public ClassModelSaveEditClassDialog(ClassModel model, DefaultGraphCell cell, ClassModelGraph graph, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.cell = cell;
        this.graph = graph;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newClassName = this.dialog.getClassName();
        if (!newClassName.equals(model.getTypeName())) {
            if (newClassName.matches("^[A-Za-z][0-9A-Za-z]*$")) {
                LOG.info("Changing the name of the class");
                model.setTypeName(newClassName);
            } else {
                LOG.warning("Bad name of the class!");
            }
        }
        graph.getGraphLayoutCache().editCell(this.cell, new HashMap());
        this.dialog.dispose();
    }
}
