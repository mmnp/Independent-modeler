package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.Cardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;

/**
 * Date: 12.11.2010
 * Time: 19:20:47
 * @author Lucky
 */
public class RelationModel implements IRelation {

//    private IClass sourceClass;
//    private IClass targetClass;
    private RelationType type;
//    private Cardinality sourceCardinality;
//    private Cardinality targetCardinality;
    private DefaultEdge cell;

//    public RelationModel(IClass sourceClass, IClass targetClass, RelationType type, Cardinality sourceCardinality, Cardinality targetCardinality) {
//        this.sourceClass = sourceClass;
//        this.targetClass = targetClass;
//        this.type = type;
//        this.sourceCardinality = sourceCardinality;
//        this.targetCardinality = targetCardinality;
//    }
    public RelationModel(RelationType type) {
        this.type = type;
        this.cell = null;
    }

    public void setCell(DefaultEdge cell) {
        this.cell = cell;
    }

    @Override
    public IClass getStartingClass() {
//        return this.sourceClass;
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getSource();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public IClass getEndingClass() {
//        return this.targetClass;
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getTarget();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public Cardinality getStartCardinality() {
//        return this.sourceCardinality;
        return Cardinality.ONE;
    }

    @Override
    public Cardinality getEndCardinality() {
//        return this.targetCardinality;
        return Cardinality.ONE;
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
}
