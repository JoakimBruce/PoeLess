package se.newmarksaur.poeless.compiler.reader.conditions;

import static org.junit.Assert.*;

import org.junit.Test;

import se.newmarksaur.poeless.compiler.environment.conditions.QualityCondition;
import se.newmarksaur.poeless.compiler.exceptions.NoSuchOperatorException;

/**
 * <p>
 * Tests for the QualityReader class.
 * </p>
 * @author      Joakim Bruce
 */
public class QualityConditionReaderTests
{
    /**
     * Tests that the reader is able to read a very basic quality condition.
     */
    @Test
    public final void shouldReadASimpleQualityCondition()
    {
        //Given
        final String simpleQualityCondition = "Quality > 10";

        //When
        try
        {
            final QualityCondition qc =
                    QualityConditionReader.readQualityCondition(simpleQualityCondition);

            //Then
            assertNotNull(qc);
            assertEquals(simpleQualityCondition, qc.toString());
        }
        catch (NoSuchOperatorException noSuchOperatorException)
        {
            assertTrue(noSuchOperatorException.getMessage(), false);
        }
    }
}
