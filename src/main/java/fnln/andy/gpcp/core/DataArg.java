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
 */
public class DataArg {
    private List<Object> m_Args;
    
    public DataArg()
    {
        m_Args = new ArrayList<>();
    }
    
    public DataArg(List<Object> args)
    {
        m_Args = args;
    }
    
    public Object getArg(final int index)
    {
        if (index >= m_Args.size())
            return null;
        
        return m_Args.get(index);
    }
    
    public void setArg(final int index, Object element)
    {
        if (index >= m_Args.size())
            return;
        
        m_Args.set(index, element);
    }
    
    public void pushBackArg(Object element)
    {
        m_Args.addLast(element);
    }
    
    public void pushFrontArg(Object element)
    {
        m_Args.addFirst(element);
    }
    
    public Object popBackArg()
    {
        final int lastIndex = m_Args.size() - 1;
        
        if (lastIndex < 0)
            return null;
        
        Object retVal = m_Args.get(lastIndex);
        
        m_Args.removeLast();
        return retVal;
    }
    
    public Object popFrontArg()
    {
        if (m_Args.isEmpty())
            return null;
        
        Object retVal = m_Args.getFirst();
        
        m_Args.removeFirst();
        return retVal;
    }
    
    public boolean isEmpty()
    {
        return m_Args.isEmpty();
    }
    
    public static DataArg makeDataArg(Object[] objects)
    {
        DataArg args = new DataArg();
        
        for (Object obj : objects)
            args.pushBackArg(obj);
        
        return args;
    }
    
    public static DataArg makeDataArg(Object object)
    {
        DataArg args = new DataArg();
        
        args.pushBackArg(object);
        
        return args;
    }
}
