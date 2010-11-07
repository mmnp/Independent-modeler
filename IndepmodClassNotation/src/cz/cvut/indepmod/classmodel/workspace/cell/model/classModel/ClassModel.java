package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IClass;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:16:40
 * <p/>
 * Model of a Class in the Class diagram.
 */
public class ClassModel extends TypeModel implements IClass {

    private static int counter = 0;

    private Set<MethodModel> methodModels;
    private Set<AttributeModel> attributeModels;

    public ClassModel() {
        this("Class"+ ++counter);
    }

    /**
     * Creates new ClassModel with no attributeModels and no methodModels
     *
     * @param name name of new class
     */
    public ClassModel(String name) {
        this(name, null, null);
    }

    public ClassModel(ClassModel model) {
        super(model.toString());

        this.methodModels = new HashSet<MethodModel>(model.getMethodModels());
        this.attributeModels = new HashSet<AttributeModel>(model.getAttributeModels());
    }

    /**
     * Creates new ClassModel
     *
     * @param name            name of new class
     * @param methodModels    Set of methodModels of this class. This class will create new Set according to this.
     * @param attributeModels Set of attributeModels of this class. This class will create new Set according to this.
     */
    public ClassModel(String name, Set<MethodModel> methodModels, Set<AttributeModel> attributeModels) {
        super(name);

        if (methodModels != null) {
            this.methodModels = new HashSet<MethodModel>(methodModels);
        } else {
            this.methodModels = new HashSet<MethodModel>();
        }

        if (attributeModels != null) {
            this.attributeModels = new HashSet<AttributeModel>(attributeModels);
        } else {
            this.attributeModels = new HashSet<AttributeModel>();
        }
    }

    @Override
    public String toString() {
        return this.getTypeName();
    }


    /**
     * Returns a view of the method set
     *
     * @return a view of the method set
     */
    @Override
    public Set<MethodModel> getMethodModels() {
        //return Collections.unmodifiableSet(methodModels);
        return new HashSet<MethodModel>(this.methodModels);
    }

    /**
     * Returns a view of the attribute set
     *
     * @return a view of the attribute set
     */
    @Override
    public Set<AttributeModel> getAttributeModels() {
        //return Collections.unmodifiableSet(attributeModels);
        return new HashSet<AttributeModel>(this.attributeModels);
    }

    /**
     * Adds new attribute to this class model. If this attribute is already here
     * it will not be apended
     * @param attr new attribute to add
     */
    public void addAttribute(AttributeModel attr) {
        this.attributeModels.add(attr);
        this.fireModelChanged();
    }

    /**
     * Removes the attribute from this class model (sure if the attribute is
     * here).
     * @param attr
     */
    public void removeAttribute(AttributeModel attr) {
        this.attributeModels.remove(attr);
        this.fireModelChanged();
    }

    /**
     * Removes the method from this class model if there is this method.
     * @param method a method to be removed from this model
     */
    public void removeMethod(MethodModel method) {
        this.methodModels.remove(method);
        this.fireModelChanged();
    }

    /**
     * Adds the method to this class if it is not already here
     * @param method a method to be added to this model
     */
    public void addMethod(MethodModel method) {
        this.methodModels.add(method);
        this.fireModelChanged();
    }
}
