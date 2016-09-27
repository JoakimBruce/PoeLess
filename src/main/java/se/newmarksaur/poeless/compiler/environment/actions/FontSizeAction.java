package se.newmarksaur.poeless.compiler.environment.actions;

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

    private int mFontSize;

    public int getFontSize()
    {
        return mFontSize;
    }

    public void setFontSize(int fontSize)
    {
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
