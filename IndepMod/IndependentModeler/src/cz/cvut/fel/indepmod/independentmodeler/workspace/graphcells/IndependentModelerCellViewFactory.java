package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class IndependentModelerCellViewFactory extends DefaultCellViewFactory {

    /**
     * @param object
     * @return
     */
    @Override
    final protected VertexView createVertexView(final Object object) {
        if (object instanceof Cell) {
            return createVertexView((Cell) object);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param cell
     * @return
     */
    private VertexView createVertexView(final Cell cell) {
        return cell.getVertexView();
    }
}
