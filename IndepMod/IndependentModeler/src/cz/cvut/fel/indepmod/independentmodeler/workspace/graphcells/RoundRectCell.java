/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class RoundRectCell extends Cell {

    public RoundRectCell() {
        this(null);
    }

    public RoundRectCell(Object userObject) {
        super(userObject);
    }

    @Override
    public VertexView getVertexView() {
        return new RoundRectView(this);
    }


}
