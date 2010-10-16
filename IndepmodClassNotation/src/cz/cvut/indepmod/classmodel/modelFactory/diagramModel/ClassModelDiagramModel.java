package cz.cvut.indepmod.classmodel.modelFactory.diagramModel;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellViewFactory;
import org.jgraph.graph.GraphLayoutCache;

import javax.swing.undo.UndoManager;

public class ClassModelDiagramModel {

    private GraphLayoutCache layoutCache;
    private UndoManager undoManager;

    public ClassModelDiagramModel() {
        this.layoutCache = new GraphLayoutCache(
                new ClassModelGraphModel(),
                new ClassModelCellViewFactory()
        );

        this.undoManager = new UndoManager();
        this.layoutCache.getModel().addUndoableEditListener(this.undoManager);
    }

    public GraphLayoutCache getLayoutCache() {
        return layoutCache;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

}
