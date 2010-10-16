package cz.cvut.indepmod.classmodel.frames.dialogs;


import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:14:49
 * <p/>
 * This class represents a dialog view which will be used for editing of a class (class in a notation's diagram)
 */
//TODO - static string data should be loaded by resource bundle
public class ClassModelEditClassDialogView extends JDialog {

    public static final String TITLE = "Class Edit";
    public static final String NAME_LABEL = "Name";
    public static final String SAVE_BUTTON = "Edit";

    private JLabel classNameLabel = new JLabel(NAME_LABEL);
    private JTextField classNameField = new JTextField();
    private JButton saveButton = new JButton(SAVE_BUTTON);


    public ClassModelEditClassDialogView(Frame owner) {
        super(owner, TITLE, true);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridLayout(2, 2));

        this.add(this.classNameLabel);
        this.add(this.classNameField);
        this.add(this.saveButton);
    }
}
