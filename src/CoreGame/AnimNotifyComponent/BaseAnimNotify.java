package CoreGame.AnimNotifyComponent;

public class BaseAnimNotify
{
    private final int FrameStart, FrameFinish;

    public int getFrameStart(){return FrameStart;}
    public int getFrameFinish(){return FrameFinish;}

    public BaseAnimNotify(int frameStart, int frameFinish)
    {
        FrameStart = frameStart;
        FrameFinish = frameFinish;
    }

    public boolean ReceiveNotifyBegin(){return false;}

    public boolean ReceiveNotifyEnd(){ return false;}

    public boolean ReceiveNotifyTick(){ return  false;}

}
