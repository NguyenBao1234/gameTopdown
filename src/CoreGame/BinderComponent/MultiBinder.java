package CoreGame.BinderComponent;

import java.util.ArrayList;
import java.util.List;

public class MultiBinder
{

    private List<CallbackFunction> assignedFunction = new ArrayList<>();

    public void bind(CallbackFunction function)
    {
        assignedFunction.add(function);
    }

    public boolean execute()
    {
        if (!assignedFunction.isEmpty()) {
            for (CallbackFunction function : assignedFunction) {
                function.execute();
            }
            return true;
        } else {
            return false;
        }
    }
}
