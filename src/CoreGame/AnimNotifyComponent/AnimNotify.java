package CoreGame.AnimNotifyComponent;

public abstract class AnimNotify
{
    private int FrameStart, FrameFinish;

    public int getFrameStart(){return FrameStart;}
    public int getFrameFinish(){return FrameFinish;}
    public void setFrameStart(int newFrame) {FrameStart = newFrame;}
    public void setFrameFinish(int newFrame) {FrameFinish = newFrame;}

    public AnimNotify(int frameStart, int frameFinish)
    {
        FrameStart = frameStart;
        FrameFinish = frameFinish;
    }

    abstract public void ReceiveNotifyBegin();

    abstract public void ReceiveNotifyEnd();

    abstract public void ReceiveNotifyTick();

}
