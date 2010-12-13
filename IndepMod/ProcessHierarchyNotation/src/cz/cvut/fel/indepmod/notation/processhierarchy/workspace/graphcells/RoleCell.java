package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.Cell;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class RoleCell extends Cell {

    public RoleCell() {
        this(null);
    }

    public RoleCell(Object o) {
        super(o);
    }

    @Override
    public VertexView getVertexView() {
        return new RoleView(this);
    }
}
