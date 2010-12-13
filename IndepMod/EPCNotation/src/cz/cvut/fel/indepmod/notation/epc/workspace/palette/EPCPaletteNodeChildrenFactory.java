package cz.cvut.fel.indepmod.notation.epc.workspace.palette;

import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.PaletteNode;
import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.PaletteNodeChildrenFactory;
import java.util.List;

/**
 *
 * @author Petr Vales
 */
public class EPCPaletteNodeChildrenFactory
        extends PaletteNodeChildrenFactory {

    public EPCPaletteNodeChildrenFactory(String myNodeKey) {
        super(myNodeKey);
    }

    @Override
    protected boolean createKeys(final List<PaletteNode> toPopulate) {
        if (super.getKey().equals("EPC")) {
            for (EPCPaletteNodeModel i : EPCPaletteNodeModel.values()) {
                PaletteNode node = new PaletteNode();
                node.setName(i.name());
                toPopulate.add(node);
            }
        }
        return true;
    }
}
