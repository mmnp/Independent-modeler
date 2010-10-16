package cz.cvut.indepmod.classmodel.actions;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 19:06:03
 * <p/>
 * This action makes REDO in Class Model diagram
 */
public class ClassModelRedoAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Redo";

    private static final Logger LOG = Logger.getLogger(ClassModelRedoAction.class);

    public ClassModelRedoAction() {
        super(ACTION_NAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        ProjectDiagram diagram = ModelerSession.getProjectControlService().getSelectedDiagram();
//        if (diagram.getDiagramModel() instanceof ClassModelDiagramModel) {
//            ((ClassModelDiagramModel) diagram.getDiagramModel()).getUndoManager().redo();
//        } else {
//            LOG.error("Diagram Model is not instance of ClassModelDiagramModel");
//        }
    }
}
