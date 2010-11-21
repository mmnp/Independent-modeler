package cz.cvut.indepmod.sequencemodel.api;

/**
 * This interface will be implemented by classes that want to listen on
 * ToolChooserModel state changed
 * @author hegladan
 */
public interface ToolChooserModelListener {

    public void selectedToolChanged(ToolChooserModel.Tool newTool);
}
