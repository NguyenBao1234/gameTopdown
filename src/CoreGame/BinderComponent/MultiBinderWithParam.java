package CoreGame.BinderComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MultiBinderWithParam<T>
{
    private final List<Consumer<T>> typeAssignedFunctions = new ArrayList<>();

    public void bind(Consumer<T> function)
    {
        typeAssignedFunctions.add((Consumer<T>) function);
    }

    public boolean executeAll(T param)
    {
        if(!typeAssignedFunctions.isEmpty())
        {
            for(Consumer<T> TFunction:typeAssignedFunctions)
            {
                TFunction.accept(param);

            }
            return true;
        }
        else return false;
    }
}
