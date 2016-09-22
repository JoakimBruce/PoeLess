package se.newmarksaur.poeless.compiler.environment.conditions;

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
