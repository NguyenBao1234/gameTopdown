package CoreGame;
interface CallbackFunction<T> {
    T execute();
}
public class CallbackHandler {
    private CallbackFunction assignedFunction;

    public void bind(CallbackFunction function)
    {
        assignedFunction = function;
    }
    public boolean execute()
    {
        if(assignedFunction != null)
        {
            assignedFunction.execute();
            return true;
        }
        else return false;
    }
}
