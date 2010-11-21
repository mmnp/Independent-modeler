/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import org.jgraph.graph.Edge;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import org.jgraph.graph.DefaultPort;
import java.util.HashSet;
import java.util.Set;
import cz.cvut.indepmod.classmodel.api.model.Cardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import org.jgraph.graph.DefaultEdge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class RelationModelTest {

    public static final String CLASS_NAME = "MyClass";
    public static final String TYPE_NAME = "testType";
    public static final String ATTRIBUTE_NAME = "myAttr";
    public static final String TYPE_NAME2 = "myOwnType";
    public static final String ATTRIBUTE_NAME2 = "testAttribute";
    public static final String METHOD_NAME = "bar";
    public static final String METHOD_NAME2 = "foo";

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRelation() {
        Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME2));

        Set<MethodModel> methods = new HashSet<MethodModel>();
        methods.add(new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, null));
        methods.add(new MethodModel(new TypeModel(TYPE_NAME2), METHOD_NAME2, null));
        
        ClassModel model = new ClassModel(CLASS_NAME, methods, attributes);
        ClassModel model2 = new ClassModel(CLASS_NAME, methods, attributes);
        
        ClassModelClassCell cell1 = new ClassModelClassCell(model);
        ClassModelClassCell cell2 = new ClassModelClassCell(model2);

        DefaultPort p1 = new DefaultPort();
        DefaultPort p2 = new DefaultPort();

        cell1.add(p1);
        cell2.add(p2);

        ClassModelRelation edge = new ClassModelRelation(new RelationModel(RelationType.RELATION));
        this.initEdge(edge, p1, p2);

        RelationModel rel = (RelationModel) edge.getUserObject();
        assertEquals(model, rel.getStartingClass());
        assertEquals(model2, rel.getEndingClass());
        assertEquals(RelationType.RELATION, rel.getRelationType());
    }

    private void initEdge(Edge edge, Port startPort, Port endPort) {
        edge.setSource(startPort);
        edge.setTarget(endPort);

        GraphConstants.setEndFill(edge.getAttributes(), true);
        GraphConstants.setLineStyle(edge.getAttributes(), GraphConstants.STYLE_ORTHOGONAL);
        GraphConstants.setLabelAlongEdge(edge.getAttributes(), false);
        GraphConstants.setEditable(edge.getAttributes(), true);
        GraphConstants.setMoveable(edge.getAttributes(), true);
        GraphConstants.setDisconnectable(edge.getAttributes(), false);
    }

}