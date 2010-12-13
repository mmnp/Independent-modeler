package cz.cvut.fel.indepmod.independentmodeler.workspace.palette;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Petr Vales
 */
public class PaletteNodeChildrenFactory extends ChildFactory < PaletteNode > {

    private final String key;

    public PaletteNodeChildrenFactory(final String myNodeKey) {
        this.key = myNodeKey;
    }

    public String getKey() {
        return key;
    }

    @Override
    protected boolean createKeys(final List < PaletteNode > toPopulate) {
        if (this.getKey().equals("Common")) {
            PaletteNode myNode = new PaletteNode();
            myNode.setName("Note");
            toPopulate.add(myNode);
            myNode = new PaletteNode();
            myNode.setName("Dependency");
            toPopulate.add(myNode);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(final PaletteNode myNodeKey) {
        Node n = new AbstractNode(Children.LEAF) {

            @Override
            public Transferable drag() throws IOException {
                return myNodeKey;
            }

        };
        n.setDisplayName(myNodeKey.getName());
        return n;
    }
}

