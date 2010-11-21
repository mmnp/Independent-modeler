package cz.cvut.indepmod.sequencemodel.toolchooser;


import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
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
    protected JToggleButton interactionButton = new JToggleButton(ToolChooserModel.TOOL_INTERACTION_NAME);
    protected JToggleButton lifelineButton = new JToggleButton(ToolChooserModel.TOOL_LIFELINE_NAME);
    protected JToggleButton messageButton = new JToggleButton(ToolChooserModel.TOOL_MESSAGE_NAME);

    public ToolChooserView() {
        this.setLayout(new GridLayout(20, 1));
        this.initLayout();
    }


    public void disableToolChooser() {
        this.interactionButton.setEnabled(false);
        this.lifelineButton.setEnabled(false);
        this.messageButton.setEnabled(false);
    }

    public void enableToolChooser() {
        this.interactionButton.setEnabled(true);
        this.lifelineButton.setEnabled(true);
        this.messageButton.setEnabled(true);
    }


    private void initLayout() {
        this.add(this.interactionButton);
        this.add(this.lifelineButton);
        this.add(this.messageButton);

        this.buttonGroup.add(this.interactionButton);
        this.buttonGroup.add(this.lifelineButton);
        this.buttonGroup.add(this.messageButton);
    }
}
