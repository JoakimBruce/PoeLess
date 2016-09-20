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
        assertNotNull(mNameSpace);
        assertTrue(mNameSpace.isEmpty());
    }

    /**
     * Tests that you can retrieve the value of a variable that has been added to the name space.
     */
    @Test
    public final void shouldBeAbleToHoldAVariable()
    {
        //Given
        String variableName = "foo";
        Object variableValue = "I am foo";

        //When
        mNameSpace.addVariable(variableName, variableValue);

        //Then
        Object readValue = mNameSpace.getVaraible(variableName);
        assertNotNull(readValue);
        assertEquals((String) readValue, (String) variableValue);
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
        Object absentVaraibleValue = mNameSpace.getVaraible(absentVariableName);

        //Then
        assertNull(absentVaraibleValue);
    }
}
