//Copyright POWGameStd
package POWJ.BinderComponent;

import java.util.ArrayList;
import java.util.List;

public class MultiBinder
{
    private final List<Runnable> runnableAssignedFunctions = new ArrayList<>();

    public void bind(Runnable function)
    {
        runnableAssignedFunctions.add(function);
    }

    public void unbind(Runnable function)
    {
        runnableAssignedFunctions.remove(function);
    }
    public boolean executeAll()
    {
        if (!runnableAssignedFunctions.isEmpty())
        {
            for (Runnable function : runnableAssignedFunctions)
            {
                function.run();
                //System.out.println(function);
            }
            return true;
        }
        else return false;
    }
}
