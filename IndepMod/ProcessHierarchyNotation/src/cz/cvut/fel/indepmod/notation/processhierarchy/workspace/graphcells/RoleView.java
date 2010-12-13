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
public class RoleView extends VertexView {

    private static transient CellViewRenderer renderer = new RoleRenderer();

    public RoleView() {
        super();
    }

    public RoleView(Object cell) {
        super(cell);
    }

    protected static void setRenderer(ProcessRenderer aRenderer) {
        renderer = aRenderer;
    }

    @Override
    public CellViewRenderer getRenderer() {
        return renderer;
    }
}
