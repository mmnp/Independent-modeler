package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.Cardinality;
import cz.cvut.indepmod.classmodel.api.model.RelationType;

/**
 * Date: 30.11.2010
 * Time: 15:42:12
 * @author Lucky
 */
public class GeneralizationModel extends AbstractRelationModel {

    public GeneralizationModel() {
    }

    @Override
    public Cardinality getStartCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public Cardinality getEndCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public RelationType getRelationType() {
        return RelationType.GENERALIZATION;
    }


}
