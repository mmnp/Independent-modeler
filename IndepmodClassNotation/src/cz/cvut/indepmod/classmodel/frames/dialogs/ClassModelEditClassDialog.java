package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.ClassModelCancelEditClassDialog;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogAddAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogRemoveAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelSaveEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ModelListener;
import java.awt.event.ActionEvent;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.openide.windows.WindowManager;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:41:40
 */
public class ClassModelEditClassDialog extends ClassModelEditClassDialogView implements ModelListener {

    private static final Logger LOG = Logger.getLogger(ClassModelEditClassDialog.class.getName());
    private ClassModel classModel;
    private ClassModelGraph graph;
    private DefaultGraphCell cell;

    public ClassModelEditClassDialog(Frame owner, ClassModelGraph graph, DefaultGraphCell cell, ClassModel cellModel) {
        super(owner);
        this.classModel = cellModel;
        this.graph = graph;
        this.cell = cell;

        this.initAction();
        this.initValues();
        this.initHandlers();
        this.setSizes();
    }

    public AttributeModel getSelectedAttribute() {
        return (AttributeModel) attributeList.getSelectedValue();
    }

    private void initHandlers() {
        this.classModel.addListener(this);
    }

    private void initValues() {
        String typeName = this.classModel.getTypeName();

        this.classNameField.setText(typeName);
        this.classNameField.setSelectionStart(0);
        this.classNameField.setSelectionEnd(typeName.length());

        this.attributeList.setModel(new DefaultListModel()); //TODO - List in  JAVA SE 7 is a generic type
        this.loadListValues();
    }

    private void loadListValues() {
        Set<AttributeModel> attributes = this.classModel.getAttributeModels();
        DefaultListModel model = (DefaultListModel) this.attributeList.getModel();

        model.clear();
        for (AttributeModel attr : attributes) {
            model.addElement(attr);
        }
    }

    private void initAction() {
        this.removeAttributeButton.addActionListener(new ClassModelEditClassDialogRemoveAttribute(this.graph, this.cell, this.classModel, this));

        this.addAttributeButton.addActionListener(new ClassModelEditClassDialogAddAttribute(this.classModel, this.cell, this.graph));

        this.saveButton.addActionListener(new ClassModelSaveEditClassDialog(this.classModel, this.cell, this.graph, this));

        this.cancelButton.addActionListener(new ClassModelCancelEditClassDialog(this));
    }

    @Override
    public void dispose() {
        this.classModel.removeListener(this);
        super.dispose();
    }

    @Override
    public void modelChanged() {
        this.loadListValues();
    }
}
