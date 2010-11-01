package cz.cvut.indepmod.classmodel.persistence.xml;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.modelFactory.ClassModelDiagramModelFactory;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class ClassModelXMLCoderTest {

    public static final String CLASS_NAME = "TestClass";
    public static final String FILE_NAME = "TestClass";

    private ClassModelGraph graph;

    public ClassModelXMLCoderTest() {
    }

    @Before
    public void setUp() {
        this.graph = new ClassModelGraph(new HashMap<String, ClassModelAbstractAction>(), new ToolChooserModel());
        this.graph.setGraphLayoutCache(ClassModelDiagramModelFactory.getInstance().createEmptyDiagramModel().getLayoutCache());
    }

    @After
    public void tearDown() {
        File f = new File(FILE_NAME);
        f.delete();
    }

    @Test
    public void testSimpleEncodeDecode() throws FileNotFoundException {
        DefaultGraphCell cell = new DefaultGraphCell(new ClassModel(CLASS_NAME));
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle.Double(10, 10, 100, 60));
        this.graph.getGraphLayoutCache().insert(cell);

        ClassModelXMLCoder encoder = new ClassModelXMLCoder();
        encoder.encode(this.graph.getGraphLayoutCache(), FILE_NAME);

        this.graph.setGraphLayoutCache(encoder.decode(FILE_NAME));

        assertEquals(1, this.graph.getModel().getRootCount());

        boolean isThere = false;
        for (TypeModel m : this.graph.getAllTypes()) {
            if (m.getTypeName().equals(CLASS_NAME)) {
                isThere = true;
            }
        }
        assertTrue(isThere);

        DefaultGraphCell root = (DefaultGraphCell)this.graph.getRoots()[0];
        ClassModel model = (ClassModel)root.getUserObject();
        assertEquals(CLASS_NAME, model.getTypeName());
        assertTrue(model.getAttributeModels().isEmpty());
        assertTrue(model.getMethodModels().isEmpty());
    }

}