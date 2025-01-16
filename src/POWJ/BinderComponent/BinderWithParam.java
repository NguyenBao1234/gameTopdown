//Copyright POWGameStd
package POWJ.BinderComponent;

import java.util.function.Consumer;

public class BinderWithParam<T>
{
    private Consumer<T> assignedFunction;

    public void bind(Consumer<T> function)
    {
        assignedFunction = (Consumer<T>) function;
    }
    public void unbind(Consumer<T> function)
    {
        if(assignedFunction == function) assignedFunction = null;
    }
    public boolean execute(T param)
    {
        if(assignedFunction != null)
        {
            assignedFunction.accept(param);
            return true;
        }
        else return false;
    }
}
