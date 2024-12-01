package CoreGame.BinderComponent;

public class Binder
{
    private Runnable assignedFunction;

    public void bind(Runnable function)
    {
        assignedFunction = function;
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
