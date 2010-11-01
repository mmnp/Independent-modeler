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
public class TypeModelTest {

    public static final String TYPE_NAME = "type";

    private TypeModel model;


    @Before
    public void setUp() {
        this.model = new TypeModel(TYPE_NAME);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTypeName method, of class TypeModel.
     */
    @Test
    public void testGetTypeName() {
        assertEquals(TYPE_NAME, this.model.getTypeName());
    }

    /**
     * Test of setTypeName method, of class TypeModel.
     */
    @Test
    public void testSetTypeName() {
        String name = "typeTest2";
        this.model.setTypeName(name);
        assertEquals(name, this.model.getTypeName());
    }

    /**
     * Test of toString method, of class TypeModel.
     */
    @Test
    public void testToString() {
        assertEquals(TYPE_NAME, this.model.toString());
    }

}