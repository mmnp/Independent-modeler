package cz.cvut.fel.indepmod.notation.processhierarchy;

import cz.cvut.fel.indepmod.independentmodeler.workspace.Editor;
import cz.cvut.fel.indepmod.notation.processhierarchy.workspace.palette.ProcessHierarchyCategoryChildrenFactory;
import cz.cvut.fel.indepmod.notation.processhierarchy.workspace.transferhandler.ProcessHierarchyTransferHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Petr Vales
 */
public final class OpenProcessHierarchyNotationEditor
                        implements ActionListener {

    @Override
    public void actionPerformed(final ActionEvent e) {
        Editor editor = new Editor(
                            "Process Hierarchy",
                            new ProcessHierarchyCategoryChildrenFactory(),
                            new ProcessHierarchyTransferHandler());
        editor.open();
        editor.requestActive();
    }
}
