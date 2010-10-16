package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:44:21
 */
public class ClassModelEditClassDialogModel {

    private String name;
    private Set<AttributeModel> attributes;
    private Set<MethodModel> methods;

    public ClassModelEditClassDialogModel(String name, Set<AttributeModel> attributes, Set<MethodModel> methods) {
        this.name = name;

        if (attributes == null) {
            this.attributes = new HashSet<AttributeModel>();
        } else {
            this.attributes = new HashSet<AttributeModel>(attributes);
        }

        if (methods == null) {
            this.methods = new HashSet<MethodModel>();
        } else {
            this.methods = new HashSet<MethodModel>(methods);
        }
    }
}
