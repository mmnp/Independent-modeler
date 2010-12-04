package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import org.openide.windows.WindowManager;

/**
 * Date: 25.11.2010
 * Time: 18:00:07
 * @author Lucky
 */
public class ClassModelAnotationCreatorDialog extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Attribute Dialog";
    public static final String CREATE_BUTTON_TITLE = "Create";
    public static final String VALUE_LIST_LABEL_TITLE = "Values";
    public static final String ADD_VALUE_NAME = "Add Value";
    public static final String REMOVE_VALUE_NAME = "Remove Value";
    private static final Logger LOG = Logger.getLogger(ClassModelAnotationCreatorDialog.class.getName());
    private JTextField anotationName;
    private JLabel valueListLabel;
    private JButton createButton;
    private JButton addValueButton;
    private JButton removeValueButton;
    private JList valueList;
    private DefaultListModel valueListModel;
    private AnotationModel returnValue;

    public ClassModelAnotationCreatorDialog(Frame owner) {
        this(owner, null);
    }

    public ClassModelAnotationCreatorDialog(Frame owner, AnotationModel returnValue) {
        super(owner, TITLE);

        this.returnValue = returnValue;
        this.initLayout();
        this.initAction();
        this.setSizes();
    }

    public AnotationModel getAnotation() {
        return this.returnValue;
    }

    private void initLayout() {
        this.setLayout(new GridLayout(4, 2));

        this.anotationName = new JTextField();
        this.addValueButton = new JButton(ADD_VALUE_NAME);
        this.removeValueButton = new JButton(REMOVE_VALUE_NAME);
        this.valueListLabel = new JLabel(VALUE_LIST_LABEL_TITLE);
        this.valueListModel = new DefaultListModel();
        this.valueList = new JList(valueListModel);
        this.createButton = new JButton(CREATE_BUTTON_TITLE);


        this.add(this.anotationName);
        this.add(new JLabel());

        this.add(this.addValueButton);
        this.add(this.removeValueButton);

        this.add(this.valueListLabel);
        this.add(this.valueList);

        this.add(new JLabel());
        this.add(this.createButton);
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = anotationName.getText();
                //TODO - verify filled data!
                returnValue = new AnotationModel(name);

                Object[] atrList = valueListModel.toArray();
                for (int i = 0; i < atrList.length; i++) {
                    returnValue.addAttribute((AnotationAttributeModel) atrList[i]);
                }
                dispose();
            }
        });

        this.addValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AnotationAttributeModel atr = new ClassModelAnotationAttributeCreatorDialog(window).getReturnValue();

                if (atr != null) {
                    valueListModel.addElement(atr);
                    LOG.info("Added Anotation Attribute.");
                } else {
                    LOG.info("Anotation Attribute was not added.");
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
