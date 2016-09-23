package se.newmarksaur.poeless.compiler.environment.conditions;

/**
 * <p>
 * Contains the operators that are relevant for conditions.
 * </p>
 * @author      Joakim Bruce
 */
public enum ConditionOperator
{
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    EQUAL("=");

    private String mOperatorString;

    /**
     * Private constructor for ConditionOperator.
     *
     * @param operatorString The string that the operator represents.
     */
    ConditionOperator(String operatorString)
    {
        mOperatorString = operatorString;
    }

    @Override
    public String toString()
    {
        return mOperatorString;
    }

    /**
     * Returns the operator corresponding to the given string.
     *
     * @param operatorString A string corresponding to one of the operators.
     * @return The corresponding operator, or <code>null</code> if the string doesn't match any
     *     operator.
     */
    public static ConditionOperator fromString(String operatorString)
    {
        if (operatorString != null)
        {
            for (ConditionOperator operator: ConditionOperator.values())
            {
                if (operatorString.equalsIgnoreCase(operator.mOperatorString))
                {
                    return operator;
                }
            }
        }
        return null;
    }

    /**
     * Checks if this condition operator if anything else than <code>EQUAL</code>.
     *
     * @return <code>false</code> iff this is an <code>EQUAL</code> operator.
     */
    public boolean isNotEqualOperator()
    {
        return !this.equals(EQUAL);
    }
}
