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

    private static final String ERROR_VAR_DOESNT_EXIST =
        "Variable %s does not exist in the name space.";
    private static final String ERROR_VAR_SHOULDNT_EXIST =
        "Variable %s should not exist in the name space.";
    private static final String ERROR_VAR_HAS_NO_VALUE =
        "Variable %s should have a value.";
    private static final String ERROR_VAR_HAS_WRONG_VALUE =
        "Variable %s has value %s, expected %s.";

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
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);

        //Then
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_1_NAME),
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

        //Then
        //Order should not matter
        final String readSecondValue = (String) mNameSpace.getVaraible(VARIABLE_2_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_2_NAME), readSecondValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_2_NAME,
                readSecondValue,
                VARIABLE_2_VALUE),
            VARIABLE_2_VALUE, readSecondValue);

        final String readFirstValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_1_NAME), readFirstValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_1_NAME,
                readFirstValue,
                VARIABLE_1_VALUE),
            VARIABLE_1_VALUE, readFirstValue);

        final String readThirdValue = (String) mNameSpace.getVaraible(VARIABLE_3_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_3_NAME), readThirdValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_3_NAME,
                readThirdValue,
                VARIABLE_3_VALUE),
            VARIABLE_3_VALUE, readThirdValue);
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
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_1_NAME,
                variableValue,
                VARIABLE_ALTERNATE_VALUE),
            VARIABLE_ALTERNATE_VALUE, variableValue);
    }

    /**
     * Tests that a copy of a name space is properly separated from the original.
     */
    @Test
    public void shouldCopyToANewNameSpace()
    {
        //Given
        mNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        final NameSpace copiedNameSpace = mNameSpace.copy();
        mNameSpace.addVariable(VARIABLE_2_NAME, VARIABLE_2_VALUE);

        //Then
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_1_NAME),
            copiedNameSpace.variableExists(VARIABLE_1_NAME));
        assertFalse(String.format(ERROR_VAR_SHOULDNT_EXIST, VARIABLE_2_NAME),
            copiedNameSpace.variableExists(VARIABLE_2_NAME));

        //When
        copiedNameSpace.addVariable(VARIABLE_1_NAME, VARIABLE_ALTERNATE_VALUE);

        //Then
        final String readFirstValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_1_NAME,
                readFirstValue,
                VARIABLE_1_VALUE),
            VARIABLE_1_VALUE, readFirstValue);
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
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_1_NAME),
            mNameSpace.variableExists(VARIABLE_1_NAME));
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_2_NAME),
            mNameSpace.variableExists(VARIABLE_2_NAME));
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_3_NAME),
            mNameSpace.variableExists(VARIABLE_3_NAME));

        final String readFirstValue = (String) mNameSpace.getVaraible(VARIABLE_1_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_1_NAME), readFirstValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_1_NAME,
                readFirstValue,
                VARIABLE_1_VALUE),
            VARIABLE_1_VALUE, readFirstValue);

        final String readSecondValue = (String) mNameSpace.getVaraible(VARIABLE_2_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_2_NAME), readSecondValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_2_NAME,
                readSecondValue,
                VARIABLE_ALTERNATE_VALUE),
            VARIABLE_ALTERNATE_VALUE, readSecondValue);

        final String readThirdValue = (String) mNameSpace.getVaraible(VARIABLE_3_NAME);
        assertNotNull(String.format(ERROR_VAR_HAS_NO_VALUE, VARIABLE_3_NAME), readThirdValue);
        assertEquals(String.format(ERROR_VAR_HAS_WRONG_VALUE,
                VARIABLE_3_NAME,
                readThirdValue,
                VARIABLE_3_VALUE),
            VARIABLE_3_VALUE, readThirdValue);

        assertFalse(String.format(ERROR_VAR_SHOULDNT_EXIST, VARIABLE_1_NAME),
            nameSpaceToAdd.variableExists(VARIABLE_1_NAME));
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_2_NAME),
            nameSpaceToAdd.variableExists(VARIABLE_2_NAME));
        assertTrue(String.format(ERROR_VAR_DOESNT_EXIST, VARIABLE_3_NAME),
            nameSpaceToAdd.variableExists(VARIABLE_3_NAME));
    }
}
