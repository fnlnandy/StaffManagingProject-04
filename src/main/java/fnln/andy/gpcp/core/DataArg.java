/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andy
 * 
 * @brief Base class to handle variable
 * numbers of args passed to a function.
 * 
 * @note Admittedly, there surely is a
 * better way to do this, but for now this
 * will do.
 */
public class DataArg {
    /**
     * @brief The 'internal' arguments that
     * this class keeps track of.
     */
    private List<Object> m_Args;
    
    /**
     * @brief Default constructor.
     */
    public DataArg()
    {
        m_Args = new ArrayList<>();
    }
    
    /**
     * @param args 
     * 
     * @brief Constructor which takes
     * a list of objects.
     */
    public DataArg(List<Object> args)
    {
        m_Args = args;
    }
    
    /**
     * @param index
     * 
     * @brief Returns the arg at a
     * specific index.
     * 
     * @note It's unused in the codebase.
     * 
     * @return 
     */
    public Object getArg(final int index)
    {
        if (index >= m_Args.size())
            return null;
        
        return m_Args.get(index);
    }
    
    /**
     * @param index
     * @param element
     * 
     * @brief Sets the arg at the specified
     * 'index' to be 'element'.
     * 
     * @note It's also unused.
     */
    public void setArg(final int index, Object element)
    {
        if (index >= m_Args.size())
            return;
        
        m_Args.set(index, element);
    }
    
    /**
     * @param element 
     * 
     * @brief Pushes back an argument to
     * the list of args.
     * 
     * @note Unused.
     */
    public void pushBackArg(Object element)
    {
        m_Args.addLast(element);
    }
    
    /**
     * @param element 
     * 
     * @brief Pushes an argument to the
     * front of the list of args.
     * 
     * @note Unused.
     */
    public void pushFrontArg(Object element)
    {
        m_Args.addFirst(element);
    }
    
    /**
     * @brief Pops the last argument, and
     * returns it.
     * 
     * @return 
     */
    public Object popBackArg()
    {
        final int lastIndex = m_Args.size() - 1;
        
        if (lastIndex < 0)
            return null;
        
        Object retVal = m_Args.get(lastIndex);
        
        m_Args.removeLast();
        return retVal;
    }
    
    /**
     * @brief Pops the first argument, and
     * returns it.
     * 
     * @note Surely the most used in this
     * codebase.
     * 
     * @return 
     */
    public Object popFrontArg()
    {
        if (m_Args.isEmpty())
            return null;
        
        Object retVal = m_Args.getFirst();
        
        m_Args.removeFirst();
        return retVal;
    }
    
    /**
     * @brief Returns if there is no more
     * arg in this class.
     * 
     * @return 
     */
    public boolean isEmpty()
    {
        return m_Args.isEmpty();
    }
    
    /**
     * @param objects
     * 
     * @brief Generates a DataArg object
     * from an array of Object.
     * 
     * @note Is used as a helper function.
     * 
     * @return 
     */
    public static DataArg makeDataArg(Object[] objects)
    {
        DataArg args = new DataArg();
        
        for (Object obj : objects)
            args.pushBackArg(obj);
        
        return args;
    }
    
    /**
     * @param object
     * 
     * @brief Generates a DataArg object
     * from a single Object.
     * 
     * @note Is also a helper function.
     * 
     * @return 
     */
    public static DataArg makeDataArg(Object object)
    {
        DataArg args = new DataArg();
        
        args.pushBackArg(object);
        
        return args;
    }
}
