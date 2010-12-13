package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.transferhandler;

import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.PaletteNode;
import cz.cvut.fel.indepmod.independentmodeler.workspace.transferhandler.IndependentModelerTransferHandler;
import cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells.DataCell;
import cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells.ProcessCell;
import cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells.RoleCell;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.jgraph.graph.DefaultGraphCell;

/**
 *
 * @author Petr Vales
 */  
public class ProcessHierarchyTransferHandler extends IndependentModelerTransferHandler {

    public ProcessHierarchyTransferHandler() {
        super();
    }

    @Override
    public boolean importData(TransferSupport support) {
        return super.importData(support);
    }

    @Override 
    protected DefaultGraphCell handleData(TransferSupport support) throws UnsupportedFlavorException, IOException {
        DefaultGraphCell[] cells = new DefaultGraphCell[1];
        cells[0] = super.handleData(support);
        PaletteNode myNode = (PaletteNode) support.getTransferable().getTransferData(PaletteNode.DATA_FLAVOR);
        if (myNode.getName().contains("Process")) {
            cells[0] = new ProcessCell(myNode.getName());
        } else if(myNode.getName().contains("Role")) {
            cells[0] = new RoleCell(myNode.getName());
        } else if(myNode.getName().contains("Data")) {
            cells[0] = new DataCell(myNode.getName());
        }
        return cells[0];
    }
}
