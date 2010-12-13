package cz.cvut.fel.indepmod.independentmodeler.workspace;

import cz.cvut.fel.indepmod.independentmodeler.workspace.actions.IndependentModelerPaletteActions;
import cz.cvut.fel.indepmod.independentmodeler.workspace.palette.CategoryChildrenFactory;
import cz.cvut.fel.indepmod.independentmodeler.workspace.transferhandler.IndependentModelerTransferHandler;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

/**
 *
 * @author Petr Vales
 */
public class Editor extends TopComponent {

    private PaletteController palette;
    private JScrollPane scenePane = new JScrollPane();
    private JGraph jGraph = new JGraph();
    private IndependentModelerTransferHandler transferHandler;

    public Editor(String title, CategoryChildrenFactory childrenfactory, IndependentModelerTransferHandler transferHandler) {
        super();
        this.transferHandler = transferHandler;
        this.setDisplayName(title);
        this.initComponents();
        this.initPalette(childrenfactory);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.initJGraph();
        this.add(this.jGraph);
        this.add(this.scenePane, BorderLayout.CENTER);
        this.scenePane.setViewportView(this.jGraph);
    }

    private void initJGraph() {
        GraphModel model = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model,
                new DefaultCellViewFactory());
        this.jGraph = new JGraph(model, view);
        initTransferHandler();
    }

    private void initTransferHandler() {
        this.transferHandler.setjGraph(jGraph);
        this.jGraph.setTransferHandler(this.transferHandler);
    }

    private void initPalette(CategoryChildrenFactory factory) {
        this.palette = PaletteFactory.createPalette(
                new AbstractNode(Children.create(
                    factory, true)),
                    new IndependentModelerPaletteActions());
        this.associateLookup(Lookups.fixed(this.palette));
    }
}
