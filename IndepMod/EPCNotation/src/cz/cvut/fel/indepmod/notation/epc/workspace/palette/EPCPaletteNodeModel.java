package cz.cvut.fel.indepmod.notation.epc.workspace.palette;

/**
 *
 * @author Petr Vales
 */
public enum EPCPaletteNodeModel {
        CONTROL,
        FUNCTION,
        EVENT,
        DELIVERABLE,
        INFORMATION_OBJECT,
        ORGANIZATION_UNIT,
        ORGANIZATION_ROLE,
        AND,
        OR,
        XOR,
        APP_SW,
        GOAL,
        MACHINE,
        HW,
        MESSAGE,
        CONTROL_FLOW_LINE, //solid line with narrow
        INFORMATION_SERVICE_FLOW_LINE, //dashed line with narrow
        INFORMATION_FLOW_LINE, //dotted line with narrow
        MATERIAL_OUTPUT_FLOW_LINE, // dot-and-dash line with narrow
        ORGANIZATION_FLOW_LINE, // solid line without narrow
        DELETE,
        AND_OR,
        AND_XOR,
        OR_AND,
        OR_XOR,
        XOR_AND,
        XOR_OR
}
