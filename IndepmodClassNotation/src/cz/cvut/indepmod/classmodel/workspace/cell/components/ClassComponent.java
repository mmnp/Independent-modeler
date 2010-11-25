package cz.cvut.indepmod.classmodel.workspace.cell.components;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:22:36
 * <p/>
 * The purpose of this class is to paint a Class representation according to its model
 */

//TODO - complete painting of the class component
public class ClassComponent extends JComponent {

    private static final Font CLASS_NAME_FONT = new Font("Serif", Font.BOLD, 15);

    private final ClassModel model;

    public ClassComponent(ClassModel model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = this.getSize();
        int namePartHeight = this.getNamePartHeight();
        int attributePartHeight = this.getAttributePartHeight();
        int methodPartHeight = this.getMethodPartHeight();
        int anotationPartHeight = this.getAnotationPartHeight();

        this.paintClassPart(g.create(0, 0, d.width, namePartHeight));
        this.paintAnotationPart(g.create(0, namePartHeight, d.width, anotationPartHeight));
        this.paintAttributePart(g.create(0, namePartHeight + anotationPartHeight, d.width, attributePartHeight));
        this.paintMethodPart(g.create(0, namePartHeight + anotationPartHeight + attributePartHeight, d.width, methodPartHeight));
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 100;
        int height = this.getNamePartHeight() + this.getAnotationPartHeight() + this.getMethodPartHeight() + this.getAttributePartHeight();
        return new Dimension(width, height);
    }

    private void paintClassPart(Graphics g) {
        int width = getSize().width;
        int height = this.getNamePartHeight();
        String className = this.model.toString();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setFont(CLASS_NAME_FONT);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(className, g);
        g.drawString(className, (int) ((width - rect.getWidth()) / 2), height - 5);
    }

    private void paintAnotationPart(Graphics g) {
        int width = getSize().width;
        int height = this.getAttributePartHeight();
        Set<AnotationModel> anots = this.model.getAnotations();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setFont(CLASS_NAME_FONT);

        int stage = 1;
        for (AnotationModel anot : anots ) {
            g.drawString(anot.toString(), 5, stage * 20 - 5);
            stage++;
        }
    }

    private void paintAttributePart(Graphics g) {
        int width = getSize().width;
        int height = this.getAttributePartHeight();
        Set<AttributeModel> attrs = this.model.getAttributeModels();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setFont(CLASS_NAME_FONT);
        //Rectangle2D rect = g.getFontMetrics().getStringBounds(className, g);

        int stage = 1;
        for (AttributeModel attr : attrs ) {
            g.drawString(attr.toString(), 5, stage * 20 - 5);
            stage++;
        }
    }

    private void paintMethodPart(Graphics g) {
        int width = getSize().width;
        int height = this.getMethodPartHeight();
        Set<MethodModel> attrs = this.model.getMethodModels();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setFont(CLASS_NAME_FONT);

        int stage = 1;
        for (MethodModel method : attrs) {
            g.drawString(method.toString(), 5, stage * 20 - 5);
            stage++;
        }
    }

    private int getNamePartHeight() {
        return 20;
    }

    private int getMethodPartHeight() {
        return 3 + this.model.getMethodModels().size() * 20;
    }

    private int getAttributePartHeight() {
        return 3 + this.model.getAttributeModels().size() * 20;
    }

    private int getAnotationPartHeight() {
        return 3 + this.model.getAnotations().size() * 20;
    }
}
