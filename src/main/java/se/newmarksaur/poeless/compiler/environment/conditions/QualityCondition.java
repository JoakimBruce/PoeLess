package se.newmarksaur.poeless.compiler.environment.conditions;

/**
 * <p>
 * A condition specifying a range of acceptable Quality values.
 * </p>
 * @author Joakim Bruce
 */
public class QualityCondition
{
    private ConditionOperator mOperator;
    private int mQuality;

    public ConditionOperator getOperator()
    {
        return mOperator;
    }

    public void setOperator(ConditionOperator operator)
    {
        mOperator = operator;
    }

    public int getQuality()
    {
        return mQuality;
    }

    public void setQuality(int quality)
    {
        mQuality = quality;
    }
}
