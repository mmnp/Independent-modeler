package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 13:23:01
 * @author Lucky
 */
public class ClassModelMethodCreatorDialog extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Method Dialog";
    public static final String LABEL_NAME = "name";
    public static final String LABEL_TYPE = "type";
    public static final String LABEL_ATTRIBUTE = "attributes";
    public static final String SAVE_BUTTON = "Save";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String ADD_ATTR_BUTTON = "Add Attribute";
    public static final String REM_ATTR_BUTTON = "Remove Attribute";
    private MethodModel returnValue = null;
    private Collection<TypeModel> availableTypes;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel attrLabel;
    private JTextField nameField;
    private JComboBox typeBox;
    private JList attrList;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton addAttrButton;
    private JButton remAttrButton;
    private DefaultListModel attributeListModel;

    public ClassModelMethodCreatorDialog(Frame owner, Collection<TypeModel> types) {
        super(owner, TITLE);

        this.availableTypes = types;
        this.attributeListModel = new DefaultListModel();
        this.nameLabel = new JLabel(LABEL_NAME);
        this.typeLabel = new JLabel(LABEL_TYPE);
        this.attrLabel = new JLabel(LABEL_ATTRIBUTE);
        this.nameField = new JTextField();
        this.typeBox = new JComboBox(this.availableTypes.toArray());
        this.attrList = new JList(this.attributeListModel);
        this.saveButton = new JButton(SAVE_BUTTON);
        this.cancelButton = new JButton(CANCEL_BUTTON);
        this.addAttrButton = new JButton(ADD_ATTR_BUTTON);
        this.remAttrButton = new JButton(REM_ATTR_BUTTON);

        this.initLayout();
        this.initAction();
        this.setSizes();
    }

    public MethodModel getMethod() {
        return this.returnValue;
    }

    private void initLayout() {
        this.setLayout(new GridLayout(5, 2));

        this.add(this.nameLabel);
        this.add(this.nameField);

        this.add(this.typeLabel);
        this.add(this.typeBox);

        this.add(this.addAttrButton);
        this.add(this.remAttrButton);

        this.add(this.attrLabel);
        this.add(this.attrList);

        this.add(this.saveButton);
        this.add(this.cancelButton);
    }

    private void initAction() {
        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                TypeModel returnType = (TypeModel) typeBox.getSelectedItem();

                if (name.matches("^[A-Za-z][0-9A-Za-z]*$")) {
                    Set<AttributeModel> attrs = new HashSet<AttributeModel>();
                    int size = attributeListModel.size();
                    for (int i = 0; i < size; i++) {
                        AttributeModel a = (AttributeModel) attributeListModel.get(i);
                        attrs.add(a);
                    }

                    returnValue = new MethodModel(returnType, name, attrs);
                    dispose();
                }
            }
        });

        this.addAttrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AttributeModel attr = new ClassModelAttributeCreatorDialog(
                        window,
                        availableTypes).getAttribute();

                if (attr != null) {
                    attributeListModel.addElement(attr);
                }
            }
        });

        this.remAttrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AttributeModel attr = (AttributeModel)attrList.getSelectedValue();
                if (attr != null) {
                    attributeListModel.removeElement(attr);
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
