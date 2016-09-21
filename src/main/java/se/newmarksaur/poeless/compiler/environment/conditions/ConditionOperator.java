package se.newmarksaur.poeless.compiler.environment.conditions;

public enum ConditionOperator
{
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    EQUAL("=");

    private String mOperatorString;

    private ConditionOperator(String operatorString)
    {
        mOperatorString = operatorString;
    }

    public String toString()
    {
        return mOperatorString;
    }

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
}
