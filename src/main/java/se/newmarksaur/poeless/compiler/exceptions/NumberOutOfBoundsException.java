package se.newmarksaur.poeless.compiler.exceptions;

import se.newmarksaur.poeless.compiler.exceptions.NumberOutOfBoundsException.Mode;

/**
 * <p>
 * Thrown when a class receives a number outside of its permitted range.
 * </p>
 * @author Joakim Bruce
 */
public class NumberOutOfBoundsException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Used to indicate if a given number is <code>LESSER</code> or <code>GREATER</code> than the
     * permitted range.
     */
    public enum Mode
    {
        GREATER("greater"),
        LESSER("lesser");

        private final String mDescription;

        /**
         * Private constructor for Mode.
         *
         * @param description A text description of the value the enum represents.
         */
        Mode(String description)
        {
            mDescription = description;
        }

        @Override
        public String toString()
        {
            return mDescription;
        }
    }

    /**
     * Creates a new exception with a message detailing which value a field received and how if
     * failed to fall within the given range.
     *
     * @param field The name of the field.
     * @param receivedNumber The inputed number that was outside the range.
     * @param rangeLimit The limit of the relevant end of the range.
     * @param mode <code>LESSER</code> if <code>receivedNumber</code> fell below the range, or
     *     <code>GREATER</code> if it fell above it.
     */
    public NumberOutOfBoundsException(String field, int receivedNumber, int rangeLimit, Mode mode)
    {
        super("The field " + field + " received a value, " + receivedNumber + ", which is "
            + mode + " than permitted limit " + rangeLimit);
    }
}
