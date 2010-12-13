/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.Cell;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class DataCell extends Cell {

    public DataCell() {
        this(null);
    }

    public DataCell(Object o) {
        super(o);
    }

    @Override
    public VertexView getVertexView() {
        return new DataView(this);
    }
}
