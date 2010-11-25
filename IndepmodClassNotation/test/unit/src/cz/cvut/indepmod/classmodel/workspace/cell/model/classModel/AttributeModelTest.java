/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class AttributeModelTest {
    private AttributeModel model;

    @Before
    public void setUp() {
        this.model = new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME);
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
        assertEquals(Common.TYPE_NAME, this.model.getType().getTypeName());
    }

    /**
     * Test of getName method, of class AttributeModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(Common.ATTRIBUTE_NAME, this.model.getName());
    }

    /**
     * Test of toString method, of class AttributeModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
        assertEquals(Common.ATTRIBUTE_NAME +" : "+ Common.TYPE_NAME, this.model.toString());
    }

}