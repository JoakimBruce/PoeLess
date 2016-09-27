package se.newmarksaur.poeless.compiler.environment.actions;

import se.newmarksaur.poeless.compiler.exceptions.NumberOutOfBoundsException;

/**
 * <p>
 * An action that sets the font size to a specific value.
 * </p>
 * @author Joakim Bruce
 */
public class FontSizeAction
{
    /**
     * The token that identifies an action as a font size action.
     */
    public static final String IDENTIFIER_FONT_SIZE = "FontSize";

    /**
     * The maximum value permitted as a font size.
     */
    public static final int MAX_SIZE = 42;

    /**
     * The minimum value permitted as a font size.
     */
    public static final int MIN_SIZE = 18;

    private static final String sFieldNameFontSize = "fontSize";

    private int mFontSize;

    public int getFontSize()
    {
        return mFontSize;
    }

    /**
     * Sets the font size this action will represent.
     *
     * @param fontSize The number to set font size to.
     * @throws NumberOutOfBoundsException If the parameter <code>fontSize</code> does not fall
     *     within the range of <code>MIN_SIZE</code> and <code>MAX_SIZE</code>.
     */
    public void setFontSize(int fontSize) throws NumberOutOfBoundsException
    {
        if (fontSize < MIN_SIZE)
        {
            throw new NumberOutOfBoundsException(sFieldNameFontSize, fontSize, MIN_SIZE,
                NumberOutOfBoundsException.Mode.LESSER);
        }
        if (fontSize > MAX_SIZE)
        {
            throw new NumberOutOfBoundsException(sFieldNameFontSize, fontSize, MAX_SIZE,
                NumberOutOfBoundsException.Mode.GREATER);
        }
        mFontSize = fontSize;
    }

    @Override
    public int hashCode()
    {
        final int prime = 53;
        int result = 1;
        result = prime * result + mFontSize;
        return result;
    }

    /**
     * Compare a FontSizeAction with this one to see if they are equal.
     *
     * @param fsa The FontSizeAction to compare this FontSizeAction with.
     * @return <code>true</code> if both FontSizeActions have the same font size value.
     */
    public boolean equals(FontSizeAction fsa)
    {
        if (fsa == null)
        {
            return false;
        }
        return mFontSize == fsa.mFontSize;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        return equals((FontSizeAction) obj);
    }
}
