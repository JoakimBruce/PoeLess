package joakimbruce.compiler;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Represents a name space, where the most recent definitions of variables are stored.
 * </p>
 * @author      Joakim Bruce
 */
public final class NameSpace implements Cloneable
{
    private static final Logger LOGGER = Logger.getLogger(NameSpace.class.getName());

    private HashMap<String, Object> mVariableRepository;

    /**
     * Creates a new name space.
     */
    public NameSpace()
    {
        mVariableRepository = new HashMap<String, Object>();
    }

    /**
     * Adds a variable to the name space, overwriting any previous value it may have had.
     * @param name The variable name.
     * @param value The value of the variable.
     */
    public void addVariable(final String name, final Object value)
    {
        mVariableRepository.put(name, value);
    }

    /**
     * Checks if the given variable exists in the name space.
     * @param name The name of the variable.
     * @return <code>true</code> if the variable exists in the name space.
     */
    public boolean variableExists(final String name)
    {
        return mVariableRepository.containsKey(name);
    }

    /**
     * Retrieves the value of a variable in the name space.
     * @param name The name of the variable.
     * @return The value of the variable, or <code>null</code> if the variable doesn't exist in the
     * name space.
     */
    public Object getVaraible(final String name)
    {
        return mVariableRepository.get(name);
    }

    /**
     * Checks if the name space contains any variables.
     * @return <code>true</code> if the name space doesn't contain any varaibles.
     */
    public boolean isEmpty()
    {
        return mVariableRepository.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public NameSpace clone()
    {
        NameSpace newNameSpace = null;
        try
        {
            newNameSpace = (NameSpace) super.clone();
            newNameSpace.mVariableRepository =
                (HashMap<String, Object>) mVariableRepository.clone();
        }
        catch (CloneNotSupportedException e)
        {
            LOGGER.log(Level.WARNING, "NameSpace unable to clone itself: " + e.toString());
        }
        return newNameSpace;
    }
}
