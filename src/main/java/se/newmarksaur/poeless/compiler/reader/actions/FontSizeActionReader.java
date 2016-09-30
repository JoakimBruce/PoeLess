package se.newmarksaur.poeless.compiler.reader.actions;

/**
 * <p>
 * A reader for the font size action.
 * </p>
 * @author Joakim Bruce
 */
public class FontSizeActionReader
{
    private FontSizeActionReader()
    {
        //Not called
    }

    /**
     *
     * @param text
     * @return
     */
    public static FontSizeAction readFontSizeAction(final String text)
    {
        return new FontSizeAction();
    }
}
