package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:44
 */
public class MethodModel {

    private TypeModel type;
    private String name;
    private Set<AttributeModel> attributeModels;

    public MethodModel(TypeModel typeModel, String name, Set<AttributeModel> attributeModels) {
        this.type = typeModel;
        this.name = name;

        if (attributeModels != null) {
            this.attributeModels = new HashSet<AttributeModel>(attributeModels);
        } else {
            this.attributeModels = new HashSet<AttributeModel>();
        }
    }

    /**
     * Returns Type instantion represeting the return type of this method
     *
     * @return Type instantion
     */
    public TypeModel getType() {
        return this.type;
    }

    /**
     * Returns the name of the method
     *
     * @return name of the method
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns an unmodifiable view of the attributes set
     *
     * @return an unmodifiable view of the attributes set
     */
    public Set<AttributeModel> getAttributeModels() {
        return Collections.unmodifiableSet(this.attributeModels);
    }
}
