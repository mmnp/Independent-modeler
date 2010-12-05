package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import org.openide.windows.WindowManager;

/**
 * Date: 17.10.2010
 * Time: 14:32:19
 * @author Lucky
 */
public class ClassModelAttributeCreatorDialog extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Attribute Dialog";
    public static final String CREATE_BUTTON_TITLE = "Create";
    public static final String ADD_ANOT_BUTTON_TITLE = "Add Anotation";
    public static final String REM_ANOT_BUTTON_TITLE = "Rem Anotation";
    private Collection<TypeModel> availableTypes;
    private AttributeModel returnValue;
    private JComboBox attributeType;
    private JTextField attributeName;
    private JButton createButton;
    private JButton addAnotationButton;
    private JButton removeAnotationButton;
    private JList anotationList;
    private DefaultListModel anotationListModel;

    public ClassModelAttributeCreatorDialog(Frame owner) {
        this(owner, new ArrayList<TypeModel>(0));
    }

    public ClassModelAttributeCreatorDialog(Frame owner, Collection<TypeModel> types) {
        super(owner, TITLE);

        this.availableTypes = types;
        this.returnValue = null;

        this.initLayout();
        this.initValues();
        this.initAction();
        this.setSizes();
    }

    public AttributeModel getAttribute() {
        return this.returnValue;
    }

    private void initLayout() {
        this.setLayout(new GridLayout(3, 1));

        this.attributeType = new JComboBox(this.availableTypes.toArray());
        this.attributeName = new JTextField();
        this.createButton = new JButton(CREATE_BUTTON_TITLE);
        this.addAnotationButton = new JButton(ADD_ANOT_BUTTON_TITLE);
        this.removeAnotationButton = new JButton(REM_ANOT_BUTTON_TITLE);
        this.anotationListModel = new DefaultListModel();
        this.anotationList = new JList(anotationListModel);

        this.add(this.attributeType);
        this.add(this.attributeName);

        this.add(this.addAnotationButton);
        this.add(this.removeAnotationButton);

        this.add(this.anotationList);
        this.add(this.createButton);
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TypeModel dateType = (TypeModel) attributeType.getSelectedItem();
                String name = attributeName.getText();

                //TODO - verify filled data!
                returnValue = new AttributeModel(dateType, name);

                Object[] objs = anotationListModel.toArray();
                for (int i = 0; i < objs.length; i++) {
                    returnValue.addAnotation((AnotationModel) objs[i]);
                }
                dispose();
            }
        });

        this.addAnotationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AnotationModel anot = new ClassModelAnotationCreatorDialog(window).getAnotation();

                if (anot != null) {
                    anotationListModel.addElement(anot);
                }
            }
        });

        this.removeAnotationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = anotationList.getSelectedIndex();
                if (index != -1) {
                    anotationListModel.remove(index);
                }
            }
        });
    }

    private void initValues() {
//        if (this.returnValue != null) {
//            this.attributeName.setText(this.returnValue.getName());
//            this.attributeType.setSelectedItem(this.returnValue.getType());
//
//            this.attributeName.setEnabled(false);
//            this.attributeType.setEnabled(false);
//        }
    }
}
