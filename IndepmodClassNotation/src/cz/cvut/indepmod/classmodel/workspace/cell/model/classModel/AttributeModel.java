package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:29
 */
public class AttributeModel extends AbstractModel implements IAttribute {

    private TypeModel type;
    private String name;
    private List<AnotationModel> anotations;

    public AttributeModel(TypeModel typeModel, String name) {
        this.type = typeModel;
        this.name = name;

        this.anotations = new ArrayList<AnotationModel>();
    }

    /**
     * Returns Type instantion represeting the type of this attribute
     *
     * @return Type instantion
     */
    @Override
    public TypeModel getType() {
        return type;
    }

    /**
     * Returns the name of this attribute
     *
     * @return the name of this attribute
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.type.getTypeName();
    }

    @Override
    public Collection<IAnotation> getAnotations() {
        return new ArrayList<IAnotation>(this.anotations);
    }

    public void addAnotation(AnotationModel anot) {
        if (anot != null && ! this.anotations.contains(anot)) {
            this.anotations.add(anot);
        }
    }

    public void removeAnotation(AnotationModel anot) {
        this.anotations.remove(anot);
    }
}
