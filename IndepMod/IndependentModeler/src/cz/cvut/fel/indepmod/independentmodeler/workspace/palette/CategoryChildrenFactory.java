package cz.cvut.fel.indepmod.independentmodeler.workspace.palette;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Petr Vales
 */
public class CategoryChildrenFactory extends ChildFactory < String > {

    public CategoryChildrenFactory() {
    }

    @Override
    protected boolean createKeys(final List < String > toPopulate) {
        toPopulate.add("Common");
        return true;
    }

    @Override
    protected Node createNodeForKey(final String key) {
        Node node = new AbstractNode(Children.create(
                        new PaletteNodeChildrenFactory(key),true));
        node.setDisplayName(key);
        return node;
    }
}

