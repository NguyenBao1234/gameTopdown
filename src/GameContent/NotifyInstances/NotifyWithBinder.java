package GameContent.NotifyInstances;

import CoreGame.AnimNotifyComponent.AnimNotify;
import CoreGame.BinderComponent.MultiBinder;

public class NotifyWithBinder extends AnimNotify
{
    private MultiBinder BoundFunction = new MultiBinder();

    public NotifyWithBinder(int frameStart, int frameFinish) {
        super(frameStart, frameFinish);
    }

    public void AddDynamic(Runnable FunctionToBind)
    {
        BoundFunction.bind(FunctionToBind);
    }
    @Override
    public void ReceiveNotifyBegin()
    {
        BoundFunction.executeAll();
    }

    @Override
    public void ReceiveNotifyEnd() {

    }

    @Override
    public void ReceiveNotifyTick() {

    }
}
