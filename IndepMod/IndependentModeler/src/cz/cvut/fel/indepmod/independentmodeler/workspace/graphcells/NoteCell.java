package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class NoteCell extends Cell {

    public NoteCell() {
        this(null);
    }

    public NoteCell(Object userObject) {
        super(userObject);
    }

    @Override
    public VertexView getVertexView() {
        return new NoteView(this);
    }


}
