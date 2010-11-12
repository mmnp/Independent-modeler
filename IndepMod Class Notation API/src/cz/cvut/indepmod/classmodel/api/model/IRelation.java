package cz.cvut.indepmod.classmodel.api.model;

/**
 * This class represents a relation between two classes. Relations contains
 * information such as starting class (from where), ending class (to where),
 * starting cardinality, ending cardinality and the type of the relation
 * (relation, aggregation, composition, generalization, ...)
 *
 * @author Lucky
 */
public interface IRelation {

    /**
     * Returns the class from where this relation starts
     * @return starting class
     */
    public IClass getStartingClass();

    /**
     * Returns the class where this relation ends
     * @return ending class
     */
    public IClass getEndingClasses();

    /**
     * Returns the starting cardinality
     * @return the starting cardinality
     */
    public Cardinality getStartCardinality();

    /**
     * Returns the ending cardinality
     * @return the ending cardinality
     */
    public Cardinality getEndCardinality();

    /**
     * Return the type of the relation (Generalization, Agregation, ...)
     * @return the type of the relation
     */
    public RelationType getRelationType();
}
