package se.newmarksaur.poeless.compiler.reader.conditions;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import se.newmarksaur.poeless.compiler.environment.conditions.ConditionOperator;
import se.newmarksaur.poeless.compiler.environment.conditions.QualityCondition;
import se.newmarksaur.poeless.compiler.exceptions.MalformedConditionException;
import se.newmarksaur.poeless.compiler.exceptions.NoSuchOperatorException;

/**
 * <p>
 * Tests for the QualityReader class.
 * </p>
 * @author      Joakim Bruce
 */
public class QualityConditionReaderTests
{
    private static final Logger LOGGER =
        Logger.getLogger(QualityConditionReaderTests.class.getName());

    /**
     * Tests that the reader is able to read a very basic quality condition.
     */
    @Test
    public final void shouldReadASimpleQualityCondition()
    {
        //Given
        final int quality = 10;
        final String simpleQualityCondition = "Quality > " + quality;

        //When
        try
        {
            final QualityCondition qc =
                    QualityConditionReader.readQualityCondition(simpleQualityCondition);

            //Then
            assertNotNull(qc);
            assertEquals(qc.toString(), simpleQualityCondition);
            assertEquals(qc.getOperator(), ConditionOperator.GREATER);
            assertEquals(qc.getQuality(), quality);
        }
        catch (MalformedConditionException malformedConditionException)
        {
            LOGGER.log(Level.SEVERE, "Failed to read condition", malformedConditionException);
            fail(malformedConditionException.getMessage());
        }
    }

    /**
     * Tests that the reader fails to parse a few representative deviations.
     */
    @Test
    public final void shouldFailToReadInvalidQualityConditions()
    {
        testForException("Qvality < 10", null);
        testForException("Quality == 10", NoSuchOperatorException.class);
        testForException("Quality < ten", NumberFormatException.class);
    }

    private void testForException(String illegalString, Class<? extends Throwable> expectedCause)
    {
        try
        {
            QualityConditionReader.readQualityCondition(illegalString);
            fail("\"" + illegalString + "\" should not be a vaild condition");
        }
        catch (MalformedConditionException malformedConditionException)
        {
            assertTrue(malformedConditionException.getMessage().contains(illegalString));
            final Throwable cause = malformedConditionException.getCause();
            if (cause != null && expectedCause != null)
            {
                assertTrue("The cause of the exception was not the expected one",
                    cause.getClass().isAssignableFrom(expectedCause));
            }
            else if (cause != null)
            {
                fail("No cause was expected");
            }
            else if (expectedCause != null)
            {
                fail("An expected cause was not found");
            }
        }
    }
}
