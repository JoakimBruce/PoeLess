package joakimbruce.compiler;

import java.util.HashMap;

/**
 * <p>
 * Represents a name space, where the most recent definitions of variables are stored.
 * </p>
 * <p>
 * © (c) Joakim Bruce
 * </p>
 */
public class NameSpace
{
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
    public final void addVariable(final String name, final Object value)
    {
        mVariableRepository.put(name, value);
    }

    /**
     * Checks if the given variable exists in the name space.
     * @param name The name of the variable.
     * @return <code>true</code> if the variable exists in the name space.
     */
    public final boolean variableExists(final String name)
    {
        return mVariableRepository.containsKey(name);
    }

    /**
     * Retrieves the value of a variable in the name space.
     * @param name The name of the variable.
     * @return The value of the variable, or <code>null</code> if the variable doesn't exist in the
     * name space.
     */
    public final Object getVaraible(final String name)
    {
        return mVariableRepository.get(name);
    }
}
