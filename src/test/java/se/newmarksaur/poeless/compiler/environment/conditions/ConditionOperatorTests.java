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
            assertTrue(noSuchOperatorException.getMessage(), false);
        }
    }
}
