/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class MethodModelTest {

    public static final String TYPE_NAME = "type";
    public static final String INT_NAME = "int";
    public static final String SINGLETON_NAME = "singleton";
    public static final String COUNT_NAME = "count";
    public static final String METHOD_NAME = "getInstance";

    private MethodModel model;

    @Before
    public void setUp() {
        Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), SINGLETON_NAME));
        attributes.add(new AttributeModel(new TypeModel(INT_NAME), COUNT_NAME));
        this.model = new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, attributes);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getType method, of class MethodModel.
     */
    @Test
    public void testGetType() {
        assertNotNull(model.getType());
        assertNotNull(model.getType().getTypeName());
        assertEquals(TYPE_NAME, model.getType().getTypeName());
    }

    /**
     * Test of getName method, of class MethodModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(METHOD_NAME, model.getName());
    }

    /**
     * Test of getAttributeModels method, of class MethodModel.
     */
    @Test
    public void testGetAttributeModels() {
        assertNotNull(model.getAttributeModels());
        Set<AttributeModel> atr = model.getAttributeModels();
        assertEquals(2, atr.size());
        for (AttributeModel a : atr) {
            assertNotNull(a);
        }
    }

    /**
     * Test of toString method, of class MethodModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
    }

}