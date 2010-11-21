package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.Cardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

/**
 * Date: 12.11.2010
 * Time: 19:20:47
 * @author Lucky
 *
 * Model of the relation. Instance of this class is used as an User Object of
 * the Edge (Edge in the JGraph...). Cell which owns this instance should
 * set the pointer to itself - information about Classes and Cardinalities
 * are gathered from the Edge...
 */
public class RelationModel implements IRelation {

    private RelationType type;
    private DefaultEdge cell;
    public RelationModel(RelationType type) {
        this.type = type;
        this.cell = null;
    }

    public void setCell(DefaultEdge cell) {
        this.cell = cell;
    }

    @Override
    public IClass getStartingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getSource();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public IClass getEndingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getTarget();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public Cardinality getStartCardinality() {
        return this.getCardinality(0);
    }

    @Override
    public Cardinality getEndCardinality() {
        return this.getCardinality(1);
    }

    @Override
    public RelationType getRelationType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "";
    }

    private void verifyCell() {
        if (this.cell == null) {
            throw new NullPointerException("Cell of RelationModel was not sat.");
        }
    }

    private Cardinality getCardinality(int index) {
        Cardinality res = null;
        try {
            Object[] cardinalities = GraphConstants.getExtraLabels(this.cell.getAttributes());
            res = (Cardinality) cardinalities[index];
        } catch (ClassCastException ex) {
            throw new ClassCastException("There is not Cardinality instance in the Extra Label of the edge!");
        }

        return res;
    }
}
