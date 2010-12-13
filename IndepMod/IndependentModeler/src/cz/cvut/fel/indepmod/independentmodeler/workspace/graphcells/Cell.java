package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
abstract public class Cell extends DefaultGraphCell{

    public Cell(Object o) {
        super(o);
    }

    public Cell() {
    }

    /**
     * 
     * @return
     */
    abstract public VertexView getVertexView();

}
