package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.palette;

import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.PaletteNode;
import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.
        PaletteNodeChildrenFactory;
import java.util.List;

/**
 *
 * @author Petr Vales
 */
public class ProcessHierarchyPaletteNodeChildrenFactory
                        extends PaletteNodeChildrenFactory {

    public ProcessHierarchyPaletteNodeChildrenFactory(String myNodeKey) {
        super(myNodeKey);
    }

    @Override
    protected boolean createKeys(final List < PaletteNode > toPopulate) {
        super.createKeys(toPopulate);
        if (super.getKey().equals("Process Hierarchy")) {
            for (ProcessHierarchyPaletteNodeModel i :
                                    ProcessHierarchyPaletteNodeModel.values()) {
                PaletteNode node = new PaletteNode();
                node.setName(i.name());
                toPopulate.add(node);
            }
        }
        return true;
    }

}
