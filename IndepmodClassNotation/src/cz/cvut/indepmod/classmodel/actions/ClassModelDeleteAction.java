package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphModel;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 18:09:11
 * <p/>
 * This action deletes selected cell in class model diagram (in JGraph) if a cel to delete is a vertex, delete its
 * relations as well
 */
public class ClassModelDeleteAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Delete";

    private static final Logger LOG = Logger.getLogger(ClassModelDeleteAction.class);

    private ClassModelGraph graph;

    public ClassModelDeleteAction(ClassModelGraph graph) {
        super(ACTION_NAME, null);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LOG.debug("deleting cells");
        Object[] cells = this.graph.getSelectionCells();

        Set<Object> cellSet = new HashSet<Object>(Arrays.asList(cells)); //Transform cells into a colection

        Set<DefaultGraphCell> vertices = this.getVertices(cellSet); //get vertices from cellSet
        Set<DefaultEdge> associatedRelations = this.getAsociatedRelations(vertices); //get Edges associated with vertices

        cellSet.addAll(associatedRelations); //add those edges (associatedRelations) into cellSet that will be deleted

        this.graph.getGraphLayoutCache().remove(cellSet.toArray());
    }

    /**
     * Returns a Set of Vertices from a Set of all cells (vertices, edges, ports)
     *
     * @param cells Set of cells
     * @return Set of vertices from set of cells
     */
    private Set<DefaultGraphCell> getVertices(Set<Object> cells) {
        Set<DefaultGraphCell> result = new HashSet<DefaultGraphCell>();

        for (Object c : cells) {
            DefaultGraphCell cell = (DefaultGraphCell) c;
            Object userObject = cell.getUserObject();
            if (userObject instanceof ClassModel) {
                result.add(cell);
            }
        }

        return result;
    }

    /**
     * Returns a set of edges those are associated with the vertices
     *
     * @param vertices set of vertices
     * @return set of edges - edges are associated with an vertex from vertices set
     */
    private Set<DefaultEdge> getAsociatedRelations(Set<DefaultGraphCell> vertices) {
        Set<DefaultEdge> result = new HashSet<DefaultEdge>();
        GraphModel model = this.graph.getModel();

        for (DefaultGraphCell vertex : vertices) {
            int children = model.getChildCount(vertex);
            for (int i = 0; i < children; i++) {
                Object port = model.getChild(vertex, i);
                if (model.isPort(port)) {
                    Iterator it = model.edges(port);
                    while (it.hasNext()) {
                        result.add((DefaultEdge) it.next());
                    }
                }
            }
        }

        return result;
    }
}
