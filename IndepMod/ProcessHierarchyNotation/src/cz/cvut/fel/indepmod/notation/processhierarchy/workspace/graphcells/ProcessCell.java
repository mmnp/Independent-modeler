package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.Cell;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class ProcessCell extends Cell {

    public ProcessCell(Object o) {
        super(o);
    }

    public ProcessCell() {
        this(null);
    }

    @Override
    public VertexView getVertexView() {
        return new ProcessView(this);
    }
}
