package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:29
 */
public class AttributeModel extends AbstractModel {
    private TypeModel type;
    private String name;

    public AttributeModel(TypeModel typeModel, String name) {
        this.type = typeModel;
        this.name = name;
    }

    /**
     * Returns Type instantion represeting the type of this attribute
     *
     * @return Type instantion
     */
    public TypeModel getType() {
        return type;
    }

    /**
     * Returns the name of this attribute
     *
     * @return the name of this attribute
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name +" : "+ this.type.getTypeName();
    }


}
