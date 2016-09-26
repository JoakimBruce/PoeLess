package se.newmarksaur.poeless.compiler.environment.conditions;

import static org.junit.Assert.*;

import org.junit.Test;

import se.newmarksaur.poeless.compiler.exceptions.NoSuchOperatorException;

/**
 * <p>
 * Tests the operators that are relevant for conditions.
 * </p>
 * @author Joakim Bruce
 */
public class ConditionOperatorTests
{

    /**
     * Tests that various strings can be parsed to their corresponding condition operator.
     */
    @Test
    public void shouldBeParsedCorrectlyFromStrings()
    {
        try
        {
            final ConditionOperator greater = ConditionOperator.fromString(">");
            assertTrue(ConditionOperator.GREATER.equals(greater));

            final ConditionOperator greaterOrEqual = ConditionOperator.fromString(">=");
            assertTrue(ConditionOperator.GREATER_OR_EQUAL.equals(greaterOrEqual));

            final ConditionOperator less = ConditionOperator.fromString("<");
            assertTrue(ConditionOperator.LESS.equals(less));

            final ConditionOperator lessOrEqual = ConditionOperator.fromString("<=");
            assertTrue(ConditionOperator.LESS_OR_EQUAL.equals(lessOrEqual));

            final ConditionOperator equal = ConditionOperator.fromString("=");
            assertTrue(ConditionOperator.EQUAL.equals(equal));
        }
        catch (NoSuchOperatorException noSuchOperatorException)
        {
            fail(noSuchOperatorException.getMessage());
        }
    }

    /**
     * Tests that an empty string defaults to <code>EQUAL</code>.
     */
    @Test
    public void shouldParseEmptyStringsAsEqual()
    {
        try
        {
            assertTrue(ConditionOperator.EQUAL.equals(ConditionOperator.fromString(null)));
            assertTrue(ConditionOperator.EQUAL.equals(ConditionOperator.fromString("")));
            assertTrue(ConditionOperator.EQUAL.equals(ConditionOperator.fromString("  ")));
        }
        catch (NoSuchOperatorException noSuchOperatorException)
        {
            fail(noSuchOperatorException.getMessage());
        }
    }

    /**
     * Tests that illegal strings yields an error.
     */
    @Test
    public void shouldGetErrorOnIllegalStrings()
    {
        testForException("==");
        testForException("EQUAL");
        testForException("=>");
        testForException("=<");
        testForException("condition");
    }

    /**
     * Tests that ConditionOperator correctly identifies when an operator is <code>EQUAL</code> or
     * not.
     */
    @Test
    public void shouldRecognizeEqualOperator()
    {
        assertTrue(ConditionOperator.GREATER.isNotEqualOperator());
        assertTrue(ConditionOperator.GREATER_OR_EQUAL.isNotEqualOperator());
        assertTrue(ConditionOperator.LESS.isNotEqualOperator());
        assertTrue(ConditionOperator.LESS_OR_EQUAL.isNotEqualOperator());
        assertFalse(ConditionOperator.EQUAL.isNotEqualOperator());
    }

    private void testForException(String illegalString)
    {
        try
        {
            ConditionOperator.fromString(illegalString);
            fail("\"" + illegalString + "\" is not an operator");
        }
        catch (NoSuchOperatorException noSuchOperatorException)
        {
            assertTrue(noSuchOperatorException.getMessage().contains(illegalString));
        }
    }
}
