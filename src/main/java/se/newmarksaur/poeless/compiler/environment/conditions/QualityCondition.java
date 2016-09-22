package se.newmarksaur.poeless.compiler.environment.conditions;

public final class QualityCondition
{
    private ConditionOperator mOperator;
    private int mQuality;

    public ConditionOperator getOperator()
    {
        return mOperator;
    }

    public void setOperator(final ConditionOperator operator)
    {
        mOperator = operator;
    }

    public int getQuality()
    {
        return mQuality;
    }

    public void setQuality(final int quality)
    {
        mQuality = quality;
    }
}
