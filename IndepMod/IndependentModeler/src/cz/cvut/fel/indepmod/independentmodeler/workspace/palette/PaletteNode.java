package cz.cvut.fel.indepmod.independentmodeler.workspace.palette;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Petr Vales
 */
public class PaletteNode implements Transferable {

    public static final  DataFlavor DATA_FLAVOR = new DataFlavor(PaletteNode.class, "MyNode");
    private String name;
    
    public PaletteNode() {
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name)   {
        this.name = name;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DATA_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == DATA_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}