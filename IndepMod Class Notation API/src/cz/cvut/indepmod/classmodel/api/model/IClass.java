/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.api.model;

import java.util.Set;

/**
 *
 * @author Lucky
 */
public interface IClass extends IType {

    /**
     * Returns an unmodifiable view of the attribute set
     *
     * @return an unmodifiable view of the attribute set
     */
    Set<? extends IAttribute> getAttributeModels();

    /**
     * Returns an unmodifiable view of the method set
     *
     * @return an unmodifiable view of the method set
     */
    Set<? extends IMethod> getMethodModels();

}
