package joakimbruce.compiler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Tests for the NameSpace class.
 * </p>
 * <p>
 * © (c) Joakim Bruce
 * </p>
 */
public class NameSpaceTests
{
    private NameSpace mNameSpace;

    /**
     * Creates a name space for tests to use.
     */
    @Before
    public final void setup()
    {
        mNameSpace = new NameSpace();
        assertNotNull("NameSpace failed to initialize", mNameSpace);
        assertTrue("NameSpace was not empty upon creation", mNameSpace.isEmpty());
    }

    /**
     * Tests that a variable that has been added to the name space exists in the name space.
     */
    @Test
    public final void shouldBeAbleToAddAVariableToTheNameSpace()
    {
        //Given
        String variableName = "foo";
        Object variableValue = "I am foo";

        //When
        mNameSpace.addVariable(variableName, variableValue);

        //Then
        assertTrue("Variable " + variableName + " does not exist in the name space",
            mNameSpace.variableExists(variableName));
    }

    /**
     * Tests that you can retrieve the values of variables that has been added to the name space.
     */
    @Test
    public final void shouldBeAbleToHoldSeveralVariables()
    {
        //Given
        String firstVariableName = "foo";
        Object firstVariableValue = "I am foo";
        String secondVariableName = "bar";
        Object secondVariableValue = "bar I am";
        String thirdVariableName = "chum";
        Object thirdVariableValue = "chum for I";

        //When
        mNameSpace.addVariable(firstVariableName, firstVariableValue);
        mNameSpace.addVariable(secondVariableName, secondVariableValue);
        mNameSpace.addVariable(thirdVariableName, thirdVariableValue);

        //Then
        Object readSecondValue = mNameSpace.getVaraible(secondVariableName);
        assertNotNull("Variable " + secondVariableName + " has no value", readSecondValue);
        assertEquals("The retrieved vaule of " + secondVariableName + ", " + readSecondValue
            + ", does not match the original value, " + secondVariableValue,
            (String) readSecondValue, (String) secondVariableValue);

        Object readFirstValue = mNameSpace.getVaraible(firstVariableName);
        assertNotNull("Variable " + firstVariableName + " has no value", readFirstValue);
        assertEquals("The retrieved vaule of " + firstVariableName + ", " + readFirstValue
            + ", does not match the original value, " + firstVariableValue,
            (String) readFirstValue, (String) firstVariableValue);

        Object readThirdValue = mNameSpace.getVaraible(thirdVariableName);
        assertNotNull("Variable " + thirdVariableName + " has no value", readThirdValue);
        assertEquals("The retrieved vaule of " + thirdVariableName + ", " + readThirdValue
            + ", does not match the original value, " + thirdVariableValue,
            (String) readThirdValue, (String) thirdVariableValue);
    }

    /**
     * Tests that a non-existent variable has no value.
     */
    @Test
    public final void shouldNotReturnAnythingForNonexistantVaraible()
    {
        //Given
        String presentVariableName = "foo";
        Object presentVariableValue = "I am foo";
        String absentVariableName = "bar";

        //When
        mNameSpace.addVariable(presentVariableName, presentVariableValue);
        Object absentVariableValue = mNameSpace.getVaraible(absentVariableName);

        //Then
        assertNull("Unintialized variable " + absentVariableName + " has a non-null value",
            absentVariableValue);
    }

    /**
     * Tests that old variable values are overwritten with new ones.
     */
    @Test
    public final void shouldOverwriteVariableValues()
    {
        //Given
        String variableName = "foo";
        Object oldVariableValue = "I am foo";
        Object newVariableValue = "barbarbar";

        //When
        mNameSpace.addVariable(variableName, oldVariableValue);
        mNameSpace.addVariable(variableName, newVariableValue);

        //Then
        Object variableValue = mNameSpace.getVaraible(variableName);
        assertEquals("Variable " + variableName + " is not updated with the new value "
            + newVariableValue + ", and instead is " + variableValue,
            (String) variableValue, (String) newVariableValue);
    }
}
