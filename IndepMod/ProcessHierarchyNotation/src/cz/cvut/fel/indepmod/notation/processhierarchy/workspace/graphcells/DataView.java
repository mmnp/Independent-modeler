/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class DataView extends VertexView {

    private static transient CellViewRenderer renderer = new DataRenderer();

    public

     DataView(Object o) {
        super(o);
    }

    public DataView() {
    }

    protected static void setRenderer(ProcessRenderer aRenderer) {
        renderer = aRenderer;
    }

    @Override
    public CellViewRenderer getRenderer() {
        return renderer;
    }
}
