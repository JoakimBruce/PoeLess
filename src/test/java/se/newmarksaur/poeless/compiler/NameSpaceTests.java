package se.newmarksaur.poeless.compiler;

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
        String variableValue = "I am foo";

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
        String firstVariableValue = "I am foo";
        String secondVariableName = "bar";
        String secondVariableValue = "bar I am";
        String thirdVariableName = "chum";
        String thirdVariableValue = "chum for I";

        //When
        mNameSpace.addVariable(firstVariableName, firstVariableValue);
        mNameSpace.addVariable(secondVariableName, secondVariableValue);
        mNameSpace.addVariable(thirdVariableName, thirdVariableValue);

        //Then
        String readSecondValue = (String) mNameSpace.getVaraible(secondVariableName);
        assertNotNull("Variable " + secondVariableName + " has no value", readSecondValue);
        assertEquals("The retrieved vaule of " + secondVariableName + ", " + readSecondValue
            + ", does not match the original value, " + secondVariableValue,
            readSecondValue, secondVariableValue);

        String readFirstValue = (String) mNameSpace.getVaraible(firstVariableName);
        assertNotNull("Variable " + firstVariableName + " has no value", readFirstValue);
        assertEquals("The retrieved vaule of " + firstVariableName + ", " + readFirstValue
            + ", does not match the original value, " + firstVariableValue,
            readFirstValue, firstVariableValue);

        String readThirdValue = (String) mNameSpace.getVaraible(thirdVariableName);
        assertNotNull("Variable " + thirdVariableName + " has no value", readThirdValue);
        assertEquals("The retrieved vaule of " + thirdVariableName + ", " + readThirdValue
            + ", does not match the original value, " + thirdVariableValue,
            readThirdValue, thirdVariableValue);
    }

    /**
     * Tests that a non-existent variable has no value.
     */
    @Test
    public void shouldNotReturnAnythingForNonexistantVaraible()
    {
        //Given
        String presentVariableName = "foo";
        String presentVariableValue = "I am foo";
        String absentVariableName = "bar";

        //When
        mNameSpace.addVariable(presentVariableName, presentVariableValue);
        String absentVariableValue = (String) mNameSpace.getVaraible(absentVariableName);

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
        String oldVariableValue = "I am foo";
        String newVariableValue = "barbarbar";

        //When
        mNameSpace.addVariable(variableName, oldVariableValue);
        mNameSpace.addVariable(variableName, newVariableValue);

        //Then
        String variableValue = (String) mNameSpace.getVaraible(variableName);
        assertEquals("Variable " + variableName + " is " + variableValue
            + ", expexted to have been changed to " + newVariableValue,
            variableValue, newVariableValue);
    }

    /**
     * Tests that a clone of a name space is properly separated from the original.
     */
    @Test
    public void shouldCloneToANewNameSpace()
    {
        //Given
        String firstVariableName = "foo";
        String firstVariableValue = "I am foo";
        String alternateVariableValue = "Who am i?";
        String secondVariableName = "bar";
        String secondVariableValue = "bar I am";

        //When
        mNameSpace.addVariable(firstVariableName, firstVariableValue);
        NameSpace clonedNameSpace = mNameSpace.copy();
        mNameSpace.addVariable(secondVariableName, secondVariableValue);

        //Then
        assertTrue("Variable " + firstVariableName + " does not exist in the cloned name space",
            clonedNameSpace.variableExists(firstVariableName));
        assertFalse("Variable " + secondVariableName + " should not exist in the cloned name space",
            clonedNameSpace.variableExists(secondVariableName));

        //When
        clonedNameSpace.addVariable(firstVariableName, alternateVariableValue);

        //Then
        String readFirstValue = (String) mNameSpace.getVaraible(firstVariableName);
        assertEquals("Variable " + firstVariableName + " is changed to " + readFirstValue
            + ", expexted to have remained " + firstVariableValue,
            readFirstValue, firstVariableValue);
    }

    /**
     * Tests that merging of name spaces works as intended.
     */
    @Test
    public void shouldProperlyAddVariablesWhenAddingNameSpaces()
    {
        //Given
        String firstVariableName = "foo";
        String firstVariableValue = "I am foo";
        String secondVariableName = "bar";
        String secondVariableValue = "bar I am";
        String alternateSecondVariableValue = "barbarbar";
        String thirdVariableName = "chum";
        String thirdVariableValue = "chum for I";

        NameSpace nameSpaceToAdd = new NameSpace();

        //When
        mNameSpace.addVariable(firstVariableName, firstVariableValue);
        mNameSpace.addVariable(secondVariableName, secondVariableValue);

        nameSpaceToAdd.addVariable(secondVariableName, alternateSecondVariableValue);
        nameSpaceToAdd.addVariable(thirdVariableName, thirdVariableValue);

        mNameSpace.addNameSpace(nameSpaceToAdd);

        //Then
        assertTrue("Variable " + firstVariableName + " should exist in the main name space",
            mNameSpace.variableExists(firstVariableName));
        assertTrue("Variable " + secondVariableName + " should exist in the main name space",
            mNameSpace.variableExists(secondVariableName));
        assertTrue("Variable " + thirdVariableName + " should exist in the main name space",
            mNameSpace.variableExists(thirdVariableName));

        String readFirstVariable = (String) mNameSpace.getVaraible(firstVariableName);
        assertEquals("The retrieved vaule of " + firstVariableName + ", " + readFirstVariable
            + ", does not match the original value, " + firstVariableValue,
            readFirstVariable, firstVariableValue);
        String readSecondVariable = (String) mNameSpace.getVaraible(secondVariableName);
        assertEquals("The retrieved vaule of " + secondVariableName + ", " + readSecondVariable
            + ", does not match the changed value, " + alternateSecondVariableValue,
            readSecondVariable, alternateSecondVariableValue);
        String readThirdVariable = (String) mNameSpace.getVaraible(thirdVariableName);
        assertEquals("The retrieved vaule of " + thirdVariableName + ", " + readThirdVariable
            + ", does not match the added value, " + thirdVariableName,
            readThirdVariable, thirdVariableValue);

        assertFalse("Variable " + firstVariableName + " should not exist in the added name space",
            nameSpaceToAdd.variableExists(firstVariableName));
        assertTrue("Variable " + secondVariableName + " should exist in the added name space",
            nameSpaceToAdd.variableExists(secondVariableName));
        assertTrue("Variable " + thirdVariableName + " should exist in the added name space",
            nameSpaceToAdd.variableExists(thirdVariableName));
    }
}
