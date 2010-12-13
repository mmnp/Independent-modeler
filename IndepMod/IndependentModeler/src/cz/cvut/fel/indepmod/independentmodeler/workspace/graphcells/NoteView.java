package cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Petr Vales
 */
public class NoteView extends VertexView {

    private static transient NoteRenderer renderer = new NoteRenderer();

    public NoteView() {
        super();
    }

    public NoteView(Object cell) {
        super(cell);
    }

    /**
     * getArcSize calculates an appropriate arc for the corners of the rectangle
     * for boundary size cases of width and height
     */
    public static int getArcSize(int width, int height) {
        int arcSize;

        // The arc width of a activity rectangle is 1/5th of the larger
        // of the two of the dimensions passed in, but at most 1/2
        // of the smaller of the two. 1/5 because it looks nice and 1/2
        // so the arc can complete in the given dimension

        if (width <= height) {
            arcSize = height / 5;
            if (arcSize > (width / 2)) {
                arcSize = width / 2;
            }
        } else {
            arcSize = width / 5;
            if (arcSize > (height / 2)) {
                arcSize = height / 2;
            }
        }

        return arcSize;
    }

    /**
     * @param aRenderer the renderer to set
     */
    protected static void setRenderer(NoteRenderer aRenderer) {
        renderer = aRenderer;
    }

    @Override
    public CellViewRenderer getRenderer() {
        return renderer;
    }
}
