package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.Cardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;

/**
 * Date: 12.11.2010
 * Time: 19:20:47
 * @author Lucky
 */
public class RelationModel implements IRelation {

    private IClass sourceClass;
    private IClass targetClass;
    private RelationType type;
    private Cardinality sourceCardinality;
    private Cardinality targetCardinality;

    public RelationModel(IClass sourceClass, IClass targetClass, RelationType type, Cardinality sourceCardinality, Cardinality targetCardinality) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.type = type;
        this.sourceCardinality = sourceCardinality;
        this.targetCardinality = targetCardinality;
    }

    @Override
    public IClass getStartingClass() {
        return this.sourceClass;
    }

    @Override
    public IClass getEndingClass() {
        return this.targetClass;
    }

    @Override
    public Cardinality getStartCardinality() {
        return this.sourceCardinality;
    }

    @Override
    public Cardinality getEndCardinality() {
        return this.targetCardinality;
    }

    @Override
    public RelationType getRelationType() {
        return this.type;
    }

}
