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

    ConditionOperator(final String operatorString)
    {
        mOperatorString = operatorString;
    }

    public String toString()
    {
        return mOperatorString;
    }

    public static ConditionOperator fromString(final String operatorString)
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
}
