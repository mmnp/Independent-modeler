/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

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
public class AttributeModelTest {

    public static final String ATTR_NAME = "attribute";
    public static final String TYPE_NAME = "type";
    private AttributeModel model;

    @Before
    public void setUp() {
        this.model = new AttributeModel(new TypeModel(TYPE_NAME), ATTR_NAME);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getType method, of class AttributeModel.
     */
    @Test
    public void testGetType() {
        assertNotNull(model.getType());
        assertEquals(TYPE_NAME, this.model.getType().getTypeName());
    }

    /**
     * Test of getName method, of class AttributeModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(ATTR_NAME, this.model.getName());
    }

    /**
     * Test of toString method, of class AttributeModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
        assertEquals(ATTR_NAME +" : "+ TYPE_NAME, this.model.toString());
    }

}