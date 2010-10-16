package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:03
 * <p/>
 * This class represents the data type (Class name, return type of method or data type of an attribute)
 */
//TODO - type model should by created by a factory method which won't create duplicated Types
public class TypeModel {
    private String typeName;

    /**
     * Creates new Type
     *
     * @param name name of the type
     */
    public TypeModel(String name) {
        this.typeName = name;
    }

    /**
     * Returns the name of this data type
     *
     * @return the name
     */
    public String getTypeName() {
        return typeName;
    }
}
