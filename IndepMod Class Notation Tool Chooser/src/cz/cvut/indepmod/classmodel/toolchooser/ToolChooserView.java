package cz.cvut.indepmod.classmodel.toolchooser;


import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * This class represents the view of the tool chooser. Tool Chooser will be used as a Dockable Frame. User will choose
 * an action that he/she wants (e.g. add Class) by the ToolChooser
 */

public class ToolChooserView extends TopComponent {

    private static final Logger LOG = Logger.getLogger(ToolChooserView.class.getName());
    

    private ButtonGroup buttonGroup = new ButtonGroup();
    protected JToggleButton controllButton = new JToggleButton(ToolChooserModel.TOOL_CONTROL_NAME);
    protected JToggleButton addClassButton = new JToggleButton(ToolChooserModel.TOOL_ADD_CLASS_NAME);
    protected JToggleButton addRelationButton = new JToggleButton(ToolChooserModel.TOOL_ADD_RELATION);

    public ToolChooserView() {
        this.setLayout(new GridLayout(20, 1));
        this.initLayout();
    }


    public void disableToolChooser() {
        this.controllButton.setEnabled(false);
        this.addClassButton.setEnabled(false);
        this.addRelationButton.setEnabled(false);
    }

    public void enableToolChooser() {
        this.controllButton.setEnabled(true);
        this.addClassButton.setEnabled(true);
        this.addRelationButton.setEnabled(true);
    }


    private void initLayout() {
        this.add(this.controllButton);
        this.add(this.addClassButton);
        this.add(this.addRelationButton);

        this.buttonGroup.add(this.controllButton);
        this.buttonGroup.add(this.addClassButton);
        this.buttonGroup.add(this.addRelationButton);
    }
}
