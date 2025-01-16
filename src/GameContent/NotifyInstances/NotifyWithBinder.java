//Copyright POWGameStd
package GameContent.NotifyInstances;

import POWJ.AnimNotifyComponent.AnimNotify;
import POWJ.BinderComponent.MultiBinder;

public class NotifyWithBinder extends AnimNotify
{
    private MultiBinder BoundFunctions = new MultiBinder();

    public NotifyWithBinder(int frameStart, int frameFinish) {
        super(frameStart, frameFinish);
    }

    public NotifyWithBinder(int frameStart, int frameFinish, Runnable BoundFunction){
        super(frameStart, frameFinish);
        BoundFunctions.bind(BoundFunction);
    }

    public void AddDynamic(Runnable FunctionToBind)
    {
        BoundFunctions.bind(FunctionToBind);
    }
    @Override
    public void ReceiveNotifyBegin()
    {
        BoundFunctions.executeAll();
    }

    @Override
    public void ReceiveNotifyEnd() {

    }

    @Override
    public void ReceiveNotifyTick() {

    }
}
