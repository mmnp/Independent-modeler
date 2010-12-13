/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.indepmod.independentmodeler;

import cz.cvut.fel.indepmod.independentmodeler.workspace.Editor;
import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.CategoryChildrenFactory;
import cz.cvut.fel.indepmod.independentmodeler.workspace.transferhandler.IndependentModelerTransferHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class EditorOpenAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Editor editor = new Editor("Test Editor", 
                                    new CategoryChildrenFactory(),
                                    new IndependentModelerTransferHandler());
        editor.open();
        editor.requestActive();
    }
}
