package se.newmarksaur.poeless.compiler.reader.conditions;

import se.newmarksaur.poeless.compiler.environment.conditions.ConditionOperator;
import se.newmarksaur.poeless.compiler.environment.conditions.QualityCondition;

/**
 * <p>
 * A reader used for quality conditions.
 * </p>
 * @author      Joakim Bruce
 */
public final class QualityConditionReader
{
    private QualityConditionReader()
    {
        //Not called
    }

    /**
     * Reads a string and tries to parse the quality condition therein.
     *
     * @param text The text string to analyze, assumed to be a single line.
     * @return A quality condition with operator and value as specified in the input string.
     */
    public static QualityCondition readQualityCondition(final String text)
    {
        final QualityCondition qualityCondition = new QualityCondition();
        final String[] splitString = text.trim().split(" ");
        qualityCondition.setOperator(ConditionOperator.fromString(splitString[1]));
        qualityCondition.setQuality(Integer.parseInt(splitString[2]));
        return qualityCondition;
    }
}
