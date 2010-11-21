/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.sequencemodel.editor;

import cz.cvut.indepmod.sequencemodel.api.ToolChooserModel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;


/**
 *
 * @author hegladan <hegladan@fel.cvut.cz>
 */

public class SequenceModelWorkspace extends TopComponent{

    private GraphModel model;
    private GraphLayoutCache view;
    private SequenceModelGraph graph;
    private ToolChooserModel selectedTool;
    private InstanceContent lookupContent = new InstanceContent();
    private static SequenceModelWorkspace instance;
    private static final String PREFERRED_ID = "SequenceModelWorkspace";

    public SequenceModelWorkspace(){
        this.initLayout();
        setName(NbBundle.getMessage(SequenceModelWorkspace.class, "CTL_Editor"));
        setToolTipText(NbBundle.getMessage(SequenceModelWorkspace.class, "HINT_Editor"));
    }

    public static synchronized SequenceModelWorkspace getDefault() {
        if (instance == null) {
            instance = new SequenceModelWorkspace();
        }
        return instance;
    }

    public static synchronized SequenceModelWorkspace findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(SequenceModelWorkspace.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof SequenceModelWorkspace) {
            return (SequenceModelWorkspace) win;
        }
        Logger.getLogger(SequenceModelWorkspace.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    private void initLayout() {
        this.model = new DefaultGraphModel();
        this.selectedTool = new ToolChooserModel();
        this.view = new GraphLayoutCache(model, new DefaultCellViewFactory());

        this.graph = new SequenceModelGraph(selectedTool);
        this.graph.setMarqueeHandler(new SequenceModelMarqueeHandler(this.graph,selectedTool));
        this.graph.setGraphLayoutCache(view);
        //this.graph.getModel().addGraphModelListener(this);

        //initCells();
        initLookup();
        this.setLayout(new GridLayout(1,1));
        this.add(new JScrollPane(this.graph));

    }

    private void initCells(){
        DefaultGraphCell[] cells = new DefaultGraphCell[3];
        cells[0] = new DefaultGraphCell(new String(":Lifeline1"));
        GraphConstants.setBounds(cells[0].getAttributes(), new Rectangle2D.Double(20, 20, 40, 30));
        GraphConstants.setGradientColor(
                cells[0].getAttributes(),
                Color.orange);
        GraphConstants.setOpaque(cells[0].getAttributes(), true);
        DefaultPort port0 = new DefaultPort();
        cells[0].add(port0);
        cells[1] = new DefaultGraphCell(new String(""));
        GraphConstants.setBounds(cells[1].getAttributes(), new Rectangle2D.Double(40, 140, 1, 1));
        GraphConstants.setGradientColor(
                cells[1].getAttributes(),
                Color.red);
        GraphConstants.setOpaque(cells[1].getAttributes(), true);
        DefaultPort port1 = new DefaultPort();
        cells[1].add(port1);
        DefaultEdge edge = new DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        cells[2] = edge;
        int arrow = GraphConstants.PERMILLE;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);
        graph.getGraphLayoutCache().insert(cells);
    }

    private void initLookup() {
        this.associateLookup(new AbstractLookup(this.lookupContent));
        this.lookupContent.add(this.selectedTool);
        this.lookupContent.add(this.model);
    }

}
