package cz.cvut.indepmod.classmodel.modelFactory;

import cz.cvut.indepmod.classmodel.modelFactory.diagramModel.ClassModelDiagramModel;

/**
 * Created by IntelliJ IDEA.
 * User: martin
 * Date: 9.8.2010
 * Time: 13:24:06
 */
public class ClassModelDiagramModelFactory {

    private static ClassModelDiagramModelFactory singleton = new ClassModelDiagramModelFactory();

    public static ClassModelDiagramModelFactory getInstance() {
        return singleton;
    }


    public ClassModelDiagramModel createEmptyDiagramModel() {
        return new ClassModelDiagramModel();
    }
}
