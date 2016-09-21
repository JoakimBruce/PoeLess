package joakimbruce.compiler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Tests for the NameSpace class.
 * </p>
 * @author      Joakim Bruce
 */
public final class NameSpaceTests
{
    private NameSpace mNameSpace;

    /**
     * Creates a name space for tests to use.
     */
    @Before
    public void setup()
    {
        mNameSpace = new NameSpace();
        assertNotNull("NameSpace failed to initialize", mNameSpace);
        assertTrue("NameSpace was not empty upon creation", mNameSpace.isEmpty());
    }

    /**
     * Tests that a variable that has been added to the name space exists in the name space.
     */
    @Test
    public void shouldBeAbleToAddAVariableToTheNameSpace()
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
    public void shouldBeAbleToHoldSeveralVariables()
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
    public void shouldNotReturnAnythingForNonexistantVaraible()
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
    public void shouldOverwriteVariableValues()
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
        assertEquals("Variable " + variableName + " is " + variableValue
            + ", expexted to have been changed to " + newVariableValue,
            (String) variableValue, (String) newVariableValue);
    }

    /**
     * Tests that a clone of a name space is properly separated from the original.
     */
    @Test
    public void shouldCloneToANewNameSpace()
    {
        //Given
        String firstVariableName = "foo";
        Object firstVariableValue = "I am foo";
        Object alternateVariableValue = "Who am i?";
        String secondVariableName = "bar";
        Object secondVariableValue = "bar I am";

        //When
        mNameSpace.addVariable(firstVariableName, firstVariableValue);
        NameSpace clonedNameSpace = mNameSpace.clone();
        mNameSpace.addVariable(secondVariableName, secondVariableValue);

        //Then
        assertTrue("Variable " + firstVariableName + " does not exist in the cloned name space",
            clonedNameSpace.variableExists(firstVariableName));
        assertFalse("Variable " + secondVariableName + " should not exist in the cloned name space",
            clonedNameSpace.variableExists(secondVariableName));

        //When
        clonedNameSpace.addVariable(firstVariableName, alternateVariableValue);

        //Then
        Object readFirstValue = mNameSpace.getVaraible(firstVariableName);
        assertEquals("Variable " + firstVariableName + " is changed to " + readFirstValue
            + ", expexted to have remained " + firstVariableValue,
            (String) readFirstValue, (String) firstVariableValue);
    }
}
