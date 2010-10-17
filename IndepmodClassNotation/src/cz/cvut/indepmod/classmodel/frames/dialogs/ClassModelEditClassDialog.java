package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:41:40
 */
public class ClassModelEditClassDialog extends ClassModelEditClassDialogView {

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
        this.setSizes();
    }

    private void initValues() {
        String typeName = this.classModel.getTypeName();
        this.classNameField.setText(typeName);
        this.classNameField.setSelectionStart(0);
        this.classNameField.setSelectionEnd(typeName.length());
    }

    private void initAction() {
        this.addAttributeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AttributeModel attr = new ClassModelAttributeCreatorDialog(window).getAttribute();

                classModel.addAttribute(attr);
                graph.getGraphLayoutCache().editCell(cell, new HashMap());
            }
        });

        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String newClassName = classNameField.getText();
                if (newClassName.matches("^[A-Za-z][0-9A-Za-z]*$")) {
                    LOG.info("Changing the name of the class");
                    classModel.setTypeName(newClassName);
                    graph.getGraphLayoutCache().editCell(cell, new HashMap());
                    dispose();
                } else {
                    LOG.warning("Bad name of the class!");
                }
            }
        });

        this.cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
