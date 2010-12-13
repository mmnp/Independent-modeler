/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.indepmod.notation.epc;

import cz.cvut.fel.indepmod.independentmodeler.workspace.Editor;
import cz.cvut.fel.indepmod.notation.epc.workspace.palette.EPCCategoryChildrenFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class OpenEPCNotationEditor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Editor editor = new Editor(
                            "Process Hierarchy",
                            new EPCCategoryChildrenFactory(),
                            null);
        editor.open();
        editor.requestActive();
    }
}
