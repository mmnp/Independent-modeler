package cz.cvut.fel.indepmod.notation.epc.workspace.palette;

import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.
        CategoryChildrenFactory;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Petr Vales
 */
public class EPCCategoryChildrenFactory
                    extends CategoryChildrenFactory {

    public EPCCategoryChildrenFactory() {
        super();
    }

    

    @Override
    protected boolean createKeys(final List<String> toPopulate) {
//        super.createKeys(toPopulate);
        toPopulate.add("EPC");
        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        Node node = new AbstractNode(Children.create(
                        new EPCPaletteNodeChildrenFactory(key),true));
        node.setDisplayName(key);
        return node;
    }
}
