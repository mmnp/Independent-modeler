/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelAttributeCreatorDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import org.jgraph.graph.DefaultGraphCell;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 11:40:10
 * @author Lucky
 */
public class ClassModelEditClassDialogAddAttribute extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Add attribute";
    private ClassModel model;
    private ClassModelGraph graph;
    private DefaultGraphCell cell;

    public ClassModelEditClassDialogAddAttribute(ClassModel model, DefaultGraphCell cell, ClassModelGraph graph) {
        super(ACTION_NAME, null);
        this.model = model;
        this.cell = cell;
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        AttributeModel attr = new ClassModelAttributeCreatorDialog(window, this.graph.getAllTypes()).getAttribute();

        if (attr != null) {
            this.model.addAttribute(attr);
            graph.getGraphLayoutCache().editCell(this.cell, new HashMap());
        }
    }
}
