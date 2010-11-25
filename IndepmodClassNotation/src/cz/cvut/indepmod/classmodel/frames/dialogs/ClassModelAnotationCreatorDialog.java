package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Date: 25.11.2010
 * Time: 18:00:07
 * @author Lucky
 */
public class ClassModelAnotationCreatorDialog extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Attribute Dialog";
    public static final String CREATE_BUTTON_TITLE = "Create";
    private JTextField anotationName;
    private JButton createButton;
    private AnotationModel returnValue;

    public ClassModelAnotationCreatorDialog(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
        this.initAction();
        this.setSizes();
    }

    public AnotationModel getAnotation() {
        return this.returnValue;
    }

    private void initLayout() {
        this.setLayout(new GridLayout(2, 1));

        this.anotationName = new JTextField();
        this.createButton = new JButton(CREATE_BUTTON_TITLE);

        this.add(this.anotationName);
        this.add(this.createButton);
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = anotationName.getText();
                //TODO - verify filled data!
                returnValue = new AnotationModel(name);
                dispose();
            }
        });
    }
}
