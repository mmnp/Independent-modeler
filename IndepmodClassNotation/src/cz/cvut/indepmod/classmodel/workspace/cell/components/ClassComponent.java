package cz.cvut.indepmod.classmodel.workspace.cell.components;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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

        this.paintClassPart(g.create(0, 0, d.width, namePartHeight));
        this.paintAttributePart(g.create(0, namePartHeight, d.width, attributePartHeight));
        this.paintMethodPart(g.create(0, namePartHeight + attributePartHeight, d.width, methodPartHeight));

        /*int width = d.width;
        int height = (int) Math.floor(d.height / 3);

        g.setColor(Color.BLACK);
        g.setFont(CLASS_NAME_FONT);

        String className = this.model.toString();
        Rectangle2D rect = g.getFontMetrics().getStringBounds(className, g);

        g.drawString(className, (int) ((width - rect.getWidth()) / 2), height - 5);

        g.drawRect(0, 0, width - 1, height);
        g.drawRect(0, height, width - 1, height);
        g.drawRect(0, 2 * height, width - 1, height - 1);*/
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 100;
        int height = this.getNamePartHeight() + this.getMethodPartHeight() + this.getAttributePartHeight();
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

    private void paintAttributePart(Graphics g) {
        int width = getSize().width;
        int height = this.getAttributePartHeight();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        /*g.setFont(CLASS_NAME_FONT);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(className, g);
        g.drawString(className, (int) ((width - rect.getWidth()) / 2), height - 5);*/
    }

    private void paintMethodPart(Graphics g) {
        int width = getSize().width;
        int height = this.getMethodPartHeight();

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        /*g.setFont(CLASS_NAME_FONT);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(className, g);
        g.drawString(className, (int) ((width - rect.getWidth()) / 2), height - 5);*/
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
}
