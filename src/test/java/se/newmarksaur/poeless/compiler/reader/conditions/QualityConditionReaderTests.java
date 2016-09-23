package se.newmarksaur.poeless.compiler.reader.conditions;

import static org.junit.Assert.*;

import se.newmarksaur.poeless.compiler.environment.conditions.QualityCondition;

import org.junit.Test;

/**
 * <p>
 * Tests for the QualityReader class.
 * </p>
 * @author      Joakim Bruce
 */
public class QualityConditionReaderTests
{
    /**
     * Tests that the reader is able to read a very basic quality condition
     */
    @Test
    public final void shouldReadASimpleQualityCondition()
    {
        //Given
        String simpleQualityCondition = "Quality > 10";

        //When
        QualityCondition qc = QualityConditionReader.readQualityCondition("Quality > 10");

        //Then
        assertNotNull(qc);
        assertEquals(simpleQualityCondition, qc.toString());
    }
}
