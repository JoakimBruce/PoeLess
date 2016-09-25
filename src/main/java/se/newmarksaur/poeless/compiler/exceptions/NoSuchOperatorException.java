package se.newmarksaur.poeless.compiler.exceptions;

/**
 * Throw when a string that does not correspond to any relevant operator is sent to be parsed
 * as one.
 *
 * @author Joakim Bruce
 */
public class NoSuchOperatorException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception with a message detailing that the operator string was not
     * expected.
     *
     * @param operatorString The string meant to represent an operator.
     */
    public NoSuchOperatorException(String operatorString)
    {
        super("The string \"" + operatorString + "\"does not correspond to any operator.");
    }
}
