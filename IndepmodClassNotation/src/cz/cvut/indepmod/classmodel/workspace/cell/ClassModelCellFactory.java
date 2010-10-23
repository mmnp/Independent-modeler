package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 30.9.2010
 * Time: 13:22:02
 * <p/>
 * This factory creates new cell types according to the chosen tool.
 */
public class ClassModelCellFactory {

    private static final Logger LOG = Logger.getLogger(ClassModelCellFactory.class.getName());

    public static DefaultGraphCell createCell(Point2D point, ToolChooserModel.Tool selectedTool) {
        DefaultGraphCell cell = new DefaultGraphCell();

        GraphConstants.setBounds(
                cell.getAttributes(),
                new Rectangle.Double(
                        point.getX(),
                        point.getY(),
                        0,
                        0
                )
        );

        GraphConstants.setResize(cell.getAttributes(), true);

        switch (selectedTool) {
            case TOOL_ADD_CLASS:
                cell.setUserObject(new ClassModel());
                break;
            default:
                LOG.severe("Unknown selected tool");
        }

        cell.add(new DefaultPort());

        return cell;
    }
}
