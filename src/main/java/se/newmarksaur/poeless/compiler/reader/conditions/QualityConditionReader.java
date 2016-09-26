package se.newmarksaur.poeless.compiler.reader.conditions;

import se.newmarksaur.poeless.compiler.environment.conditions.ConditionOperator;
import se.newmarksaur.poeless.compiler.environment.conditions.QualityCondition;
import se.newmarksaur.poeless.compiler.exceptions.MalformedConditionException;
import se.newmarksaur.poeless.compiler.exceptions.NoSuchOperatorException;

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
     * @throws MalformedConditionException If the given text fails to parse as a quality condition.
     */
    public static QualityCondition readQualityCondition(final String text)
            throws MalformedConditionException
    {
        final QualityCondition qualityCondition = new QualityCondition();
        final String[] splitString = text.trim().split(" ");
        if (!QualityCondition.IDENTIFIER_QUALITY.equals(splitString[0]))
        {
            throw new MalformedConditionException(QualityCondition.IDENTIFIER_QUALITY, text);
        }

        try
        {
            qualityCondition.setOperator(ConditionOperator.fromString(splitString[1]));
        }
        catch (NoSuchOperatorException noSuchOperatorException)
        {
            throw new MalformedConditionException(QualityCondition.IDENTIFIER_QUALITY, text,
                noSuchOperatorException);
        }
        try
        {
            qualityCondition.setQuality(Integer.parseInt(splitString[2]));
        }
        catch (NumberFormatException numberFormatException)
        {
            throw new MalformedConditionException(QualityCondition.IDENTIFIER_QUALITY, text,
                numberFormatException);
        }
        return qualityCondition;
    }
}
