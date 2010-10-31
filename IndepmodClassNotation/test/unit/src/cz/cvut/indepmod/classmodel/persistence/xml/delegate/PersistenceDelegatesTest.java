/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import java.util.Set;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class PersistenceDelegatesTest {

    public static final String CLASS_NAME = "TestClass";
    public static final String TYPE_NAME = "TYPETEST";
    public static final String TYPE_NAME2 = "testType";
    public static final String ATTRIBUTE_NAME = "AttrIbuteTeStTtTtttT";
    public static final String ATTRIBUTE_NAME2 = "attrTest";
    public static final String METHOD_NAME = "METHODnameTest";
    public static final String FILE_NAME = "TestFile.xml";
    private File f;
    private XMLEncoder encoder;

    @Before
    public void setUp() {
        this.f = new File(FILE_NAME);

        try {
            this.encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            this.encoder.setPersistenceDelegate(ClassModel.class, new ClassModelPersistenceDelegate());
            this.encoder.setPersistenceDelegate(TypeModel.class, new TypeModelPersistenceDelegate());
            this.encoder.setPersistenceDelegate(AttributeModel.class, new AttributeModelPersistenceDelegate());
            this.encoder.setPersistenceDelegate(MethodModel.class, new MethodModelPersistenceDelegate());
            this.encoder.setExceptionListener(new ExceptionListener() {

                @Override
                public void exceptionThrown(Exception e) {
                    fail("Error during encoding, probably there is missing some of Persistence Delegates: " + e.getMessage());
                }
            });
        } catch (FileNotFoundException ex) {
            fail();
        }
    }

    @After
    public void tearDown() {
        f.delete();
    }

    /**
     * Test of initialize method, of class TypeModelPersistenceDelegate.
     */
    @Test
    public void testTypeModelEncodeDecode() {
        TypeModel tm = new TypeModel(TYPE_NAME);
        TypeModel dtm = null;
        
        this.encoder.writeObject(tm);
        this.encoder.close();

        dtm = (TypeModel) this.decode(this.f);

        assertEquals(TYPE_NAME, dtm.getTypeName());
    }

    /**
     * Test of initialize method, of class ClassModelPersistenceDelegate.
     */
    @Test
    public void testClassModelEncodeDecode() {
        ClassModel cm = new ClassModel(CLASS_NAME);
        ClassModel dcm = null;

        this.encoder.writeObject(cm);
        this.encoder.close();

        dcm = (ClassModel) this.decode(this.f);

        assertEquals(CLASS_NAME, dcm.getTypeName());
    }

    @Test
    public void testAttributeModelEncodeDecode() {
        AttributeModel am = new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME);
        AttributeModel dam = null;

        this.encoder.writeObject(am);
        this.encoder.close();

        dam = (AttributeModel) this.decode(this.f);
        assertEquals(TYPE_NAME, dam.getType().getTypeName());
        assertEquals(ATTRIBUTE_NAME, dam.getName());
    }

    @Test
    public void testMethodModelEncodeDecode() {
        Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME), ATTRIBUTE_NAME));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(TYPE_NAME2), ATTRIBUTE_NAME2));

        MethodModel mm = new MethodModel(new TypeModel(TYPE_NAME), METHOD_NAME, attributes);
        MethodModel dmm = null;

        this.encoder.writeObject(mm);
        this.encoder.close();

        dmm = (MethodModel) this.decode(this.f);

        assertEquals(TYPE_NAME, mm.getType().getTypeName());
        assertEquals(METHOD_NAME, dmm.getName());
        assertEquals(4, dmm.getAttributeModels().size());
    }


    private Object decode(File f) {
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.f)));
            if (dec != null) {
                Object obj = dec.readObject();
                dec.close();
                return obj;
            }
        } catch (FileNotFoundException ex) {
            fail(ex.getMessage());
        }
        return null;
    }
}
