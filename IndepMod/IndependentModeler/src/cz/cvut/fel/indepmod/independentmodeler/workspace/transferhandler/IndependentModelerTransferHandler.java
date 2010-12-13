package cz.cvut.fel.indepmod.independentmodeler.workspace.transferhandler;

import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.IndependentModelerCellViewFactory;
import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.NoteCell;
import cz.cvut.fel.indepmod.independentmodeler.workspace.graphcells.RoundRectCell;
import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.PaletteNode;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.TransferHandler;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.openide.util.Exceptions;

/**
 *
 * @author Petr Vales
 */
public class IndependentModelerTransferHandler extends TransferHandler {

    private JGraph jGraph;
    private DefaultCellViewFactory viewFacotry = new IndependentModelerCellViewFactory();

    public void setjGraph(JGraph jGraph) {
        this.jGraph = jGraph;
        jGraph.getGraphLayoutCache().setFactory(this.viewFacotry);
    }

    public JGraph getjGraph() {
        return jGraph;
    }

    public DefaultCellViewFactory getViewFacotry() {
        return viewFacotry;
    }

    public IndependentModelerTransferHandler() {
        this.viewFacotry = new IndependentModelerCellViewFactory();

    }

    @Override
    public boolean canImport(final TransferSupport support) {
        return support.isDataFlavorSupported(PaletteNode.DATA_FLAVOR);
    }

    @Override
    public boolean importData(final TransferSupport support) {
        try {
            DefaultGraphCell[] cells = new DefaultGraphCell[1];
            cells[0] = this.handleData(support);
            GraphConstants.setBounds(cells[0].getAttributes(), new Rectangle2D.Double(support.getDropLocation().getDropPoint().getX(), support.getDropLocation().getDropPoint().getY(), 200, 100));
            GraphConstants.setOpaque(cells[0].getAttributes(), true);
            DefaultPort port0 = new DefaultPort();
            cells[0].add(port0);
            this.getjGraph().getGraphLayoutCache().insert(cells);
            this.getjGraph().repaint();
            return true;
        } catch (UnsupportedFlavorException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    protected DefaultGraphCell handleData(final TransferSupport support) throws UnsupportedFlavorException, IOException {
        DefaultGraphCell[] cells = new DefaultGraphCell[1];
        PaletteNode myNode = (PaletteNode) support.getTransferable().getTransferData(PaletteNode.DATA_FLAVOR);
        if (myNode.getName().contains("Note")) {
            cells[0] = new NoteCell(myNode.getName());
        } else if (myNode.getName().contains("Dependency")) {
            cells[0] = new RoundRectCell(myNode.getName());
        }
        return cells[0];
    }
}
