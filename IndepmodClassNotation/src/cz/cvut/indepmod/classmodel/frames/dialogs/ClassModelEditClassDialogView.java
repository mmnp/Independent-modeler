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
public class ClassModelEditClassDialogView extends ClassModelAbstractDialog {

    public static final String TITLE = "Class Edit";
    public static final String NAME_LABEL = "Name";
    public static final String ATTRIBUTES_LABEL = "Attributes";
    public static final String METHODS_LABEL = "Methods";
    public static final String SAVE_BUTTON = "Edit";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String ADD_ATTRIBUTE_BUTTON = "Add Atribute";
    public static final String ADD_METHOD_BUTTON = "Add Method";
    public static final String REMOVE_ATTRIBUTE_BUTTON = "Del Atribute";
    public static final String REMOVE_METHOD_BUTTON = "Del Method";

    protected JLabel classNameLabel = new JLabel(NAME_LABEL);
    protected JLabel attributesLabel = new JLabel(ATTRIBUTES_LABEL);
    protected JLabel methodsLabel = new JLabel(METHODS_LABEL);
    protected JTextField classNameField = new JTextField();
    protected JButton addAttributeButton = new JButton(ADD_ATTRIBUTE_BUTTON);
    protected JButton removeAttributeButton = new JButton(REMOVE_ATTRIBUTE_BUTTON);
    protected JButton addMethodButton = new JButton(ADD_METHOD_BUTTON);
    protected JButton removeMethodButton = new JButton(REMOVE_METHOD_BUTTON);
    protected JButton saveButton = new JButton(SAVE_BUTTON);
    protected JButton cancelButton = new JButton(CANCEL_BUTTON);
    protected JList attributeList = new JList(new DefaultListModel());
    protected JList methodList = new JList(new DefaultListModel());


    public ClassModelEditClassDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridLayout(6, 2));

        this.add(this.classNameLabel);
        this.add(this.classNameField);

        this.add(this.addAttributeButton);
        this.add(this.removeAttributeButton);

        this.add(this.attributesLabel);
        this.add(this.attributeList);

        this.add(this.addMethodButton);
        this.add(this.removeMethodButton);

        this.add(this.methodsLabel);
        this.add(this.methodList);;

        this.add(this.saveButton);
        this.add(this.cancelButton);
    }
}
