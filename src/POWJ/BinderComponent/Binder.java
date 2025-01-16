//Copyright POWGameStd
package POWJ.BinderComponent;

public class Binder
{
    private Runnable assignedFunction;

    public void bind(Runnable function)
    {
        assignedFunction = function;
    }
    public void unbind(Runnable function)
    {
        if(assignedFunction == function) assignedFunction = null;
    }
    public boolean execute()
    {
        if(assignedFunction != null)
        {
            assignedFunction.run();
            return true;
        }
        else return false;
    }
}
