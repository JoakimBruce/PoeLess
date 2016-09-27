package se.newmarksaur.poeless.compiler;

import java.util.HashMap;

/**
 * <p>
 * Represents a name space, where the most recent definitions of variables are stored.
 * </p>
 * @author      Joakim Bruce
 */
public class NameSpace
{
    private HashMap<String, Object> mVariableRepository;

    /**
     * Creates a new empty name space.
     */
    public NameSpace()
    {
        mVariableRepository = new HashMap<String, Object>();
    }

    /**
     * Adds a variable to the name space, overwriting any previous value it may have had.
     *
     * @param name The variable name.
     * @param value The value of the variable.
     */
    public void addVariable(String name, Object value)
    {
        mVariableRepository.put(name, value);
    }

    /**
     * Adds all the variables (if any) in the given name space to this one, overwriting any previous
     * declarations. Does not change anything in the given name space.
     *
     * @param nameSpaceToAdd The name space containing the variables to add to this one.
     */
    public void addNameSpace(NameSpace nameSpaceToAdd)
    {
        if (nameSpaceToAdd != null)
        {
            for (String variable: nameSpaceToAdd.mVariableRepository.keySet())
            {
                addVariable(variable, nameSpaceToAdd.getVaraible(variable));
            }
        }
    }

    /**
     * Checks if the given variable exists in the name space.
     *
     * @param name The name of the variable.
     * @return <code>true</code> if the variable exists in the name space.
     */
    public boolean variableExists(String name)
    {
        return mVariableRepository.containsKey(name);
    }

    /**
     * Retrieves the value of a variable in the name space.
     *
     * @param name The name of the variable.
     * @return The value of the variable, or <code>null</code> if the variable doesn't exist in the
     *     name space.
     */
    public Object getVaraible(String name)
    {
        return mVariableRepository.get(name);
    }

    /**
     * Checks if the name space contains any variables.
     *
     * @return <code>true</code> if the name space doesn't contain any varaibles.
     */
    public boolean isEmpty()
    {
        return mVariableRepository.isEmpty();
    }

    /**
     * Creates a copy of this name space.
     *
     * @return A copy of this name space.
     */
    public NameSpace copy()
    {
        final NameSpace newNameSpace = new NameSpace();
        newNameSpace.mVariableRepository.putAll(mVariableRepository);
        return newNameSpace;
    }
}
