package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;

/**
 * Date: 25.11.2010
 * Time: 16:24:20
 * @author Lucky
 */
public class AnotationModel implements IAnotation {

    private String name;

    public AnotationModel(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "@" + this.name;
    }



}
