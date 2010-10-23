/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 23.10.2010
 * Time: 11:43:39
 * @author Lucky
 */
public class ClassModelEditClassDialogRemoveAttribute extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Remove attribute";
    private ClassModelGraph graph;
    private DefaultGraphCell cell;
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogRemoveAttribute(ClassModelGraph graph, DefaultGraphCell cell, ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.graph = graph;
        this.cell = cell;
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AttributeModel attr = this.dialog.getSelectedAttribute();
        if (attr != null) {
            this.model.removeAttribute(attr);
            this.graph.getGraphLayoutCache().editCell(this.cell, new HashMap());
        }
    }
}
