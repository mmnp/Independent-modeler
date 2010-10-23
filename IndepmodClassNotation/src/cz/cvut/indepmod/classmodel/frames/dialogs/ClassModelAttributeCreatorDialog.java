/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Date: 17.10.2010
 * Time: 14:32:19
 * @author Lucky
 */
public class ClassModelAttributeCreatorDialog extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Attribute Dialog";

    public static final String CREATE_BUTTON_TITLE = "Create";

    private Collection<TypeModel> availableTypes;

    private AttributeModel returnValue;

    private JComboBox attributeType;
    private JTextField attributeName;
    private JButton createButton;

    public ClassModelAttributeCreatorDialog(Frame owner) {
        this(owner, new ArrayList<TypeModel>(0));
    }

    public ClassModelAttributeCreatorDialog(Frame owner, Collection<TypeModel> types) {
        super(owner, TITLE);

        this.availableTypes = types;

        this.initLayout();
        this.initAction();
        this.setSizes();
    }

    private void initLayout() {
        this.setLayout(new GridLayout(3, 1));

        this.attributeType = new JComboBox(this.availableTypes.toArray());
        this.attributeName = new JTextField();
        this.createButton = new JButton(CREATE_BUTTON_TITLE);

        this.add(this.attributeType);
        this.add(this.attributeName);
        this.add(this.createButton);
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TypeModel dateType = (TypeModel)attributeType.getSelectedItem();
                String name = attributeName.getText();

                //TODO - verify filled data!
                returnValue = new AttributeModel(dateType, name);
                dispose();
            }
        });
    }

    public AttributeModel getAttribute() {
        return this.returnValue;
    }



}
