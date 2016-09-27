package se.newmarksaur.poeless.compiler.environment.conditions;

import se.newmarksaur.poeless.compiler.exceptions.NoSuchOperatorException;

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
     * Returns the operator corresponding to the given string. Defaults to <code>EQUAL</code>
     * if no value is given, e.g. by a <code>null</code> value or an empty string.
     *
     * @param operatorString A string corresponding to one of the operators, or <code>EQUAL</code>
     *     if the string contains no value.
     * @return The corresponding operator.
     * @throws NoSuchOperatorException If the string doesn't correspond to any operator.
     */
    public static ConditionOperator fromString(String operatorString)
            throws NoSuchOperatorException
    {
        if (operatorString == null)
        {
            return EQUAL;
        }
        final String trimmedOperatorString = operatorString.trim();
        if (!trimmedOperatorString.isEmpty())
        {
            for (ConditionOperator operator: ConditionOperator.values())
            {
                if (trimmedOperatorString.equalsIgnoreCase(operator.mOperatorString))
                {
                    return operator;
                }
            }
            throw new NoSuchOperatorException(operatorString);
        }
        return EQUAL;
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
