package se.newmarksaur.poeless.compiler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Tests for the NameSpace class.
 * </p>
 * @author      Joakim Bruce
 */
public final class NameSpaceTests
{
    private static final String VARIABLE_1_NAME = "foo";
    private static final String VARIABLE_1_VALUE = "foofoofoo";
    private static final String VARIABLE_2_NAME = "bar";
    private static final String VARIABLE_2_VALUE = "barbarbar";
    private static final String VARIABLE_3_NAME = "chum";
    private static final String VARIABLE_3_VALUE = "chumchumchum";
    private static final String VARIABLE_ALTERNATE_VALUE = "vavava";

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
        //When
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);

        //Then
        assertTrue("Variable " + VARIABLE_1_NAME + " does not exist in the name space",
            mNameSpace.variableExists(VARIABLE_1_NAME));
    }

    /**
     * Tests that you can retrieve the values of variables that has been added to the name space.
     */
    @Test
    public void shouldBeAbleToHoldSeveralVariables()
    {
        //Given
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        mNameSpace.addVariable(VARIABLE_2_NAME, VARIABLE_2_VALUE);
        mNameSpace.addVariable(VARIABLE_3_NAME, VARIABLE_3_VALUE);

        //When
        final String readSecondValue = (String) mNameSpace.getVaraible(VARIABLE_2_NAME);
        final String readFirstValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        final String readThirdValue = (String) mNameSpace.getVaraible(VARIABLE_3_NAME);

        //Then
        assertNotNull(hasNoValue(VARIABLE_1_NAME), readFirstValue);
        assertEquals("The retrieved vaule of " + VARIABLE_1_NAME + ", " + readFirstValue
            + ", does not match the original value, " + VARIABLE_1_VALUE,
            readFirstValue, VARIABLE_1_VALUE);

        assertNotNull(hasNoValue(VARIABLE_2_NAME), readSecondValue);
        assertEquals("The retrieved vaule of " + VARIABLE_2_NAME + ", " + readSecondValue
            + ", does not match the original value, " + VARIABLE_2_VALUE,
            readSecondValue, VARIABLE_2_VALUE);

        assertNotNull(hasNoValue(VARIABLE_3_NAME), readThirdValue);
        assertEquals("The retrieved vaule of " + VARIABLE_3_NAME + ", " + readThirdValue
            + ", does not match the original value, " + VARIABLE_3_VALUE,
            readThirdValue, VARIABLE_3_VALUE);
    }

    /**
     * Tests that a non-existent variable has no value.
     */
    @Test
    public void shouldNotReturnAnythingForNonexistantVaraible()
    {
        //Given
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);

        //When
        final String absentVariableValue = (String) mNameSpace.getVaraible(VARIABLE_2_NAME);

        //Then
        assertNull("Unintialized variable " + VARIABLE_2_NAME + " has a non-null value",
            absentVariableValue);
    }

    /**
     * Tests that old variable values are overwritten with new ones.
     */
    @Test
    public void shouldOverwriteVariableValues()
    {
        //When
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_ALTERNATE_VALUE);

        //Then
        final String variableValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertEquals("Variable " + VARIABLE_1_NAME + " is " + variableValue
            + ", expexted to have been changed to " + VARIABLE_ALTERNATE_VALUE,
            variableValue, VARIABLE_ALTERNATE_VALUE);
    }

    /**
     * Tests that a copy of a name space is properly separated from the original.
     */
    @Test
    public void shouldCopyToANewNameSpace()
    {
        //When
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        final NameSpace copiedNameSpace = mNameSpace.copy();
        mNameSpace.addVariable(VARIABLE_2_NAME, VARIABLE_2_VALUE);

        //Then
        assertTrue("Variable " + VARIABLE_1_NAME + " does not exist in the copied name space",
            copiedNameSpace.variableExists(VARIABLE_1_NAME));
        assertFalse("Variable " + VARIABLE_2_NAME + " should not exist in the copied name space",
            copiedNameSpace.variableExists(VARIABLE_2_NAME));

        //When
        copiedNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_ALTERNATE_VALUE);

        //Then
        final String readFirstValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertEquals("Variable " + VARIABLE_1_NAME + " is changed to " + readFirstValue
            + ", expexted to have remained " + VARIABLE_1_VALUE,
            readFirstValue, VARIABLE_1_VALUE);
    }

    /**
     * Tests that merging of name spaces works as intended.
     */
    @Test
    public void shouldProperlyAddVariablesWhenAddingNameSpaces()
    {
        //Given
        final NameSpace nameSpaceToAdd = new NameSpace();

        //When
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        mNameSpace.addVariable(VARIABLE_2_NAME, VARIABLE_2_VALUE);

        nameSpaceToAdd.addVariable(VARIABLE_2_NAME, VARIABLE_ALTERNATE_VALUE);
        nameSpaceToAdd.addVariable(VARIABLE_3_NAME, VARIABLE_3_VALUE);

        mNameSpace.addNameSpace(nameSpaceToAdd);

        //Then
        assertTrue("Variable " + VARIABLE_1_NAME + " should exist in the main name space",
            mNameSpace.variableExists(VARIABLE_1_NAME));
        assertTrue("Variable " + VARIABLE_2_NAME + " should exist in the main name space",
            mNameSpace.variableExists(VARIABLE_2_NAME));
        assertTrue("Variable " + VARIABLE_3_NAME + " should exist in the main name space",
            mNameSpace.variableExists(VARIABLE_3_NAME));

        final String readFirstVariable = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertEquals("The retrieved vaule of " + VARIABLE_1_NAME + ", " + readFirstVariable
            + ", does not match the original value, " + VARIABLE_1_VALUE,
            readFirstVariable, VARIABLE_1_VALUE);
        final String readSecondVariable = (String) mNameSpace.getVaraible(VARIABLE_2_NAME);
        assertEquals("The retrieved vaule of " + VARIABLE_2_NAME + ", " + readSecondVariable
            + ", does not match the changed value, " + VARIABLE_ALTERNATE_VALUE,
            readSecondVariable, VARIABLE_ALTERNATE_VALUE);
        final String readThirdVariable = (String) mNameSpace.getVaraible(VARIABLE_3_NAME);
        assertEquals("The retrieved vaule of " + VARIABLE_3_NAME + ", " + readThirdVariable
            + ", does not match the added value, " + VARIABLE_3_NAME,
            readThirdVariable, VARIABLE_3_VALUE);

        assertFalse(shouldExist(VARIABLE_1_NAME), nameSpaceToAdd.variableExists(VARIABLE_1_NAME));
        assertTrue(shouldNotExist(VARIABLE_2_NAME), nameSpaceToAdd.variableExists(VARIABLE_2_NAME));
        assertTrue(shouldNotExist(VARIABLE_3_NAME), nameSpaceToAdd.variableExists(VARIABLE_3_NAME));
    }

    private String hasNoValue(String variable)
    {
        return variable + " has no value";
    }

    private String shouldExist(String variable)
    {
        return variable + " should in the given name space";
    }

    private String shouldNotExist(String variable)
    {
        return variable + " should exist in the given name space";
    }
}
