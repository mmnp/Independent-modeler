package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.ClassModelCancelEditClassDialog;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogAddAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogRemoveAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelSaveEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ModelListener;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:41:40
 *
 * This class represents dialog which is used for editing of a class.
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

    /**
     * Returns Selected Attribute in the attribute list
     * @return selected attribute
     */
    public AttributeModel getSelectedAttribute() {
        return (AttributeModel) attributeList.getSelectedValue();
    }

    /**
     * Returns filled name of the class
     * @return name of the class which is filled in the dialog
     */
    public String getClassName() {
        return this.classNameField.getText();
    }

    /**
     * This method is called when there is a change in the model and thus the
     * cell's view should be updated (e.g. attribute action adds an attribute)
     */
    public void updateCell() {
        this.graph.getGraphLayoutCache().editCell(this.cell, new HashMap());
    }

    /**
     * Returns all Types from the graph
     * @return All types which are used in the graph
     */
    public Collection<TypeModel> getAllTypeModel() {
        return this.graph.getAllTypes();
    }

    /**
     * Initializes event handlers
     */
    private void initHandlers() {
        this.classModel.addListener(this);
    }

    /**
     * Initializes values in the dialog according to the class model
     */
    private void initValues() {
        String typeName = this.classModel.getTypeName();

        this.classNameField.setText(typeName);
        this.classNameField.setSelectionStart(0);
        this.classNameField.setSelectionEnd(typeName.length());

        this.attributeList.setModel(new DefaultListModel()); //TODO - List in  JAVA SE 7 is a generic type
        this.loadListValues();
    }

    /**
     * Loads list of attributes into the attribute list which is situated in the
     * dialog
     */
    private void loadListValues() {
        Set<AttributeModel> attributes = this.classModel.getAttributeModels();
        DefaultListModel model = (DefaultListModel) this.attributeList.getModel();

        model.clear();
        for (AttributeModel attr : attributes) {
            model.addElement(attr);
        }
    }

    /**
     * Initializes actions (for saving, canceling, ...)
     */
    private void initAction() {
        this.removeAttributeButton.addActionListener(new ClassModelEditClassDialogRemoveAttribute(this.classModel, this));

        this.addAttributeButton.addActionListener(new ClassModelEditClassDialogAddAttribute(this.classModel, this));

        this.saveButton.addActionListener(new ClassModelSaveEditClassDialog(this.classModel, this));

        this.cancelButton.addActionListener(new ClassModelCancelEditClassDialog(this));
    }

    @Override
    /**
     * Disposes this dialog
     */
    public void dispose() {
        this.classModel.removeListener(this);
        super.dispose();
    }

    @Override
    public void modelChanged() {
        this.loadListValues();
    }
}
