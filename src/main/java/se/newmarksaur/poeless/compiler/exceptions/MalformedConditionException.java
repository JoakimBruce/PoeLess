package se.newmarksaur.poeless.compiler.exceptions;

/**
 * Throw when a string that is supposed to represent a specific condition fails to parse.
 *
 * @author Joakim Bruce
 */
public class MalformedConditionException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception explaining that the given string could not be parsed as a condition
     * of the proper type.
     *
     * @param expectedCondition The condition the string was expected to represent.
     * @param recievedInput The string that failed to parse.
     */
    public MalformedConditionException(String expectedCondition, String recievedInput)
    {
        super(messageString(expectedCondition, recievedInput));
    }

    /**
     * Creates a new exception explaining that the given string could not be parsed as a condition
     * of the proper type, with an reference to the error that caused this exception.
     *
     * @param expectedCondition The condition the string was expected to represent.
     * @param recievedInput The string that failed to parse.
     * @param cause The cause of this exception.
     */
    public MalformedConditionException(String expectedCondition,
                                       String recievedInput,
                                       Throwable cause)
    {
        super(messageString(expectedCondition, recievedInput), cause);
    }

    private static String messageString(String expectedCondition, String recievedInput)
    {
        return "The string \"" + recievedInput + "\" could not be parsed as a "
            + expectedCondition + " condition";
    }
}
