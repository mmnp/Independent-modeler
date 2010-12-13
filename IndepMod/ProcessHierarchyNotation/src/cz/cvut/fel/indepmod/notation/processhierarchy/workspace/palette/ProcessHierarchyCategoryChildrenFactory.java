package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.palette;

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
public class ProcessHierarchyCategoryChildrenFactory
                    extends CategoryChildrenFactory {

    public ProcessHierarchyCategoryChildrenFactory() {
        super();
    }

    

    @Override
    protected boolean createKeys(final List<String> toPopulate) {
        super.createKeys(toPopulate);
        toPopulate.add("Process Hierarchy");
        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        Node node = new AbstractNode(Children.create(
                        new ProcessHierarchyPaletteNodeChildrenFactory(key),true));
        node.setDisplayName(key);
        return node;
    }
}
