package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * Date: 4.12.2010
 * Time: 17:33:28
 * @author Lucky
 */
public class ClassModelAnotationAttributeCreatorDialog extends ClassModelAbstractDialog {
    
    public static final String DIALOG_TITLE = "Anotation Attribute";
    public static final String CREATE_BUTTON_NAME = "Create";
    public static final String ADD_VALUE_BUTTON_NAME = "Add";
    public static final String REMOVE_VALUE_BUTTON_NAME = "Remove";

    private JTextField anotAtrName;
    private JTextField valName;
    private JButton createButton;
    private JButton addValueButton;
    private JButton removeValueButton;
    private JList valueList;
    private DefaultListModel valueListModel;


    private AnotationAttributeModel returnValue;

    public ClassModelAnotationAttributeCreatorDialog(Frame owner) {
        super(owner, DIALOG_TITLE);

        this.returnValue = null;
        this.initLayout();
        this.initAction();
        this.setSizes();
    }

    public AnotationAttributeModel getReturnValue() {
        return returnValue;
    }



    private void initLayout() {
        this.setLayout(new GridLayout(3, 2));

        this.anotAtrName = new JTextField();
        this.valName = new JTextField();
        this.createButton = new JButton(CREATE_BUTTON_NAME);
        this.addValueButton = new JButton(ADD_VALUE_BUTTON_NAME);
        this.removeValueButton = new JButton(REMOVE_VALUE_BUTTON_NAME);
        this.valueListModel = new DefaultListModel();
        this.valueList = new JList(this.valueListModel);

        this.add(this.anotAtrName);
        this.add(this.createButton);

        this.add(this.valName);
        this.add(this.addValueButton);

        this.add(this.removeValueButton);
        this.add(this.valueList);
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = anotAtrName.getText();
                //TODO - verify filled data
                returnValue = new AnotationAttributeModel(name);

                Object[] objs = valueListModel.toArray();
                for (int i = 0; i < objs.length; i++) {
                    returnValue.addValue((String) objs[i]);
                }

                dispose();
            }
        });

        this.addValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = valName.getText();

                if (! name.isEmpty()) {
                    valueListModel.addElement(name);
                }
            }
        });

        this.removeValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = valueList.getSelectedIndex();
                if (index != -1) {
                    valueListModel.remove(index);
                }
            }
        });
    }


}
