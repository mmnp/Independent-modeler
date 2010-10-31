/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.persistence.xml;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.modelFactory.ClassModelDiagramModelFactory;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Rectangle;
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

    private ClassModelGraph graph;

    public ClassModelXMLCoderTest() {
    }

    @Before
    public void setUp() {
        this.graph = new ClassModelGraph(new HashMap<String, ClassModelAbstractAction>(), new ToolChooserModel());
        this.graph.setGraphLayoutCache(ClassModelDiagramModelFactory.getInstance().createEmptyDiagramModel().getLayoutCache());

        DefaultGraphCell cell = new DefaultGraphCell(new ClassModel("Test"));
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle.Double(10, 10, 100, 60));
        this.graph.getGraphLayoutCache().insert(cell);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEncodeDecode() throws FileNotFoundException {
        ClassModelXMLCoder encoder = new ClassModelXMLCoder();
        encoder.encode(this.graph.getGraphLayoutCache(), "persistenceTest.xml");

        this.graph.setGraphLayoutCache(encoder.decode("persistenceTest.xml"));

        assertEquals(1, this.graph.getModel().getRootCount());
    }

}