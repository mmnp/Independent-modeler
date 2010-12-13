package cz.cvut.fel.indepmod.notation.processhierarchy.workspace.graphcells;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class ProcessView extends VertexView {

    private static transient CellViewRenderer renderer = new ProcessRenderer();

    public ProcessView() {
        super();
    }

    public ProcessView(Object cell) {
        super(cell);
    }

    /**
     * getArcSize calculates an appropriate arc for the corners of the rectangle
     * for boundary size cases of width and height
     
//    public static int getArcSize(int width, int height) {
//        int arcSize;
//        if (width <= height) {
//            arcSize = height / 5;
//            if (arcSize > (width / 2)) {
//                arcSize = width / 2;
//            }
//        } else {
//            arcSize = width / 5;
//            if (arcSize > (height / 2)) {
//                arcSize = height / 2;
//            }
//        }
//        return arcSize;
//    }
     * /

    /**
     * @param aRenderer the renderer to set
     */
    protected static void setRenderer(ProcessRenderer aRenderer) {
        renderer = aRenderer;
    }

    @Override
    public CellViewRenderer getRenderer() {
        return renderer;
    }
}
