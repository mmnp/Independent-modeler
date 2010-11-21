package cz.cvut.indepmod.classmodel.api.model;

/**
 * This enumeration represents the cardinality of an relation. Possible
 * cardinalities are ZERO (0), ZEROINFINIRY (0..* or simple *), ONEINFINITY
 * (1..*) or ONE (1)
 *
 * @author Lucky
 */
public enum Cardinality {

    ONEINFINITY {

        @Override
        public String toString() {
            return "1..*";
        }
    },
    ONE {

        @Override
        public String toString() {
            return "1";
        }
    },
    ZEROINFINITY {

        @Override
        public String toString() {
            return "*";
        }
    },
}
