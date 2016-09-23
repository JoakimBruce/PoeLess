package se.newmarksaur.poeless.compiler.environment.conditions;

/**
 * <p>
 * A condition specifying a range of acceptable Quality values.
 * </p>
 * @author Joakim Bruce
 */
public class QualityCondition
{
    /**
     * The token that identifies a condition as a quality condition.
     */
    public static final String IDENTIFIER_QUALITY = "Quality";

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

    @Override
    public String toString()
    {
        String operator = "";
        if (mOperator.isNotEqualOperator())
        {
            operator = mOperator + " ";
        }
        return IDENTIFIER_QUALITY + " " + operator + mQuality;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mOperator == null) ? 0 : mOperator.hashCode());
        result = prime * result + mQuality;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QualityCondition other = (QualityCondition) obj;
        if (mOperator != other.mOperator)
            return false;
        if (mQuality != other.mQuality)
            return false;
        return true;
    }
}
