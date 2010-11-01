package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class ClassModelTest {

    public static final String CLASS_NAME = "MyClass";
    public static final String TYPE_NAME = "testType";
    public static final String ATTRIBUTE_NAME = "myAttr";
    public static final String TYPE_NAME2 = "myOwnType";
    public static final String ATTRIBUTE_NAME2 = "testAttribute";
    public static final String METHOD_NAME = "bar";
    public static final String METHOD_NAME2 = "foo";


    private ClassModel model;


    @Before
    public void setUp() {
         Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME2));

        Set<MethodModel> methods = new HashSet<MethodModel>();
        methods.add(new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, null));
        methods.add(new MethodModel(new TypeModel(TYPE_NAME2), METHOD_NAME2, null));
        
        this.model = new ClassModel(CLASS_NAME, methods, attributes);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClassInitialization() {
        ClassModel m = null;

        m = new ClassModel();
        assertTrue(m.getAttributeModels().isEmpty());
        assertTrue(m.getMethodModels().isEmpty());
        assertNotNull(m.getTypeName());
        assertNotNull(m.toString());

        m = new ClassModel(CLASS_NAME);
        assertTrue(m.getAttributeModels().isEmpty());
        assertTrue(m.getMethodModels().isEmpty());
        assertNotNull(m.getTypeName());
        assertNotNull(m.toString());
        assertEquals(CLASS_NAME, m.getTypeName());
        assertEquals(CLASS_NAME, m.toString());


        Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME2));

        Set<MethodModel> methods = new HashSet<MethodModel>();
        methods.add(new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, null));
        methods.add(new MethodModel(new TypeModel(TYPE_NAME2), METHOD_NAME2, null));

        m = new ClassModel(CLASS_NAME, methods, attributes);
        assertNotNull(m.getAttributeModels());
        assertNotNull(m.getMethodModels());
        assertNotNull(m.toString());
        assertNotNull(m.getTypeName());
        assertEquals(3, m.getAttributeModels().size());
        assertEquals(2, m.getMethodModels().size());
        assertEquals(CLASS_NAME, m.getTypeName());
        assertEquals(CLASS_NAME, m.toString());
    }

    /**
     * Test of toString method, of class ClassModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
    }

    /**
     * Test of getMethodModels method, of class ClassModel.
     */
    @Test
    public void testGetMethodModels() {
        assertNotNull(model.getMethodModels());
        assertEquals(2, model.getMethodModels().size());
    }

    /**
     * Test of getAttributeModels method, of class ClassModel.
     */
    @Test
    public void testGetAttributeModels() {
        assertNotNull(model.getAttributeModels());
        assertEquals(3, model.getAttributeModels().size());
    }

    /**
     * Test of addAttribute method, of class ClassModel.
     */
    @Test
    public void testAddAttribute() {
        ClassModel m = new ClassModel();
        assertEquals(0, m.getAttributeModels().size());
        
        m.addAttribute(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        assertEquals(1, m.getAttributeModels().size());

        AttributeModel a = m.getAttributeModels().iterator().next();
        assertEquals(TYPE_NAME, a.getType().getTypeName());
        assertEquals(ATTRIBUTE_NAME, a.getName());
    }

    /**
     * Test of removeAttribute method, of class ClassModel.
     */
    @Test
    public void testRemoveAttribute() {
        ClassModel m = new ClassModel();
        assertEquals(0, m.getAttributeModels().size());

        m.addAttribute(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        assertEquals(1, m.getAttributeModels().size());

        m.removeAttribute(m.getAttributeModels().iterator().next());
        assertEquals(0, m.getAttributeModels().size());
    }

    /**
     * Test of removeMethod method, of class ClassModel.
     */
    @Test
    public void testRemoveMethod() {
        ClassModel m = new ClassModel(CLASS_NAME);

        assertEquals(0, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, null));
        assertEquals(1, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(TYPE_NAME2), METHOD_NAME2, null));
        assertEquals(2, m.getMethodModels().size());

        m.removeMethod(m.getMethodModels().iterator().next());
        assertEquals(1, m.getMethodModels().size());

        m.removeMethod(m.getMethodModels().iterator().next());
        assertEquals(0, m.getMethodModels().size());
    }

    /**
     * Test of addMethod method, of class ClassModel.
     */
    @Test
    public void testAddMethod() {
        ClassModel m = new ClassModel(CLASS_NAME);

        assertEquals(0, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, null));
        assertEquals(1, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(TYPE_NAME2), METHOD_NAME2, null));
        assertEquals(2, m.getMethodModels().size());
    }

}