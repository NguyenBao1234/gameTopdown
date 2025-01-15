package CoreGame.EntityComponent;

import CoreGame.AnimNotifyComponent.AnimNotify;
import CoreGame.AnimationClass.AnimMontage;
import CoreGame.Data.Enums.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class BaseCharacter extends BaseObject
{
    protected int vAxisX;
    protected int vAxisY;
    protected int Speed = 4;

    protected AnimMontage PlayingAnimationMontage;

    private final ArrayList<Direction> directionList = new ArrayList<>(2);

    public int getSpeed(){return Speed;}

    public void setSpeed(int newSpeed) {Speed = newSpeed;}

    /**Mang chi can 'row' de chua bao nhieu flipBook (flipBook: buffered image array, tuong duong nhu 1 animation).
     * vi du: flipBookArr = new BufferImage[x][_].*/
    protected BufferedImage[][] flipBookArr;

    public BaseCharacter()
    {
        directionList.add(Direction.down);
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }

    /** Chon mang Buffered Image nao lam flipBook. Truoc khi goi ham nay can khoi tao flipBookArr[][]truoc*/
    public void SetAnimationToUse(int index, int FPSPerImage)
    {
        if(index > flipBookArr.length-1) return;
        if(PlayingAnimationMontage != null) return;
        flipBook = flipBookArr[index];
        fpsPerImage = FPSPerImage;
        //System.out.println("Setup Animation: successes");
    }

    /**Play the AnimMontage once. After that, back to the normal animation state */
    public void PlayAnimMontage(AnimMontage animMontage, int FPSPerImage)
    {
        currFlipBookFrame = -1;//ensure playing Full AnimMontage
        currFlipBookPage = -1;
        this.PlayingAnimationMontage = animMontage;
        flipBook = animMontage.getFlipBook();
        fpsPerImage = FPSPerImage;
    }

    public void PlayAnimMontage(AnimMontage animMontage)
    {
        currFlipBookFrame = -1;//ensure playing Full AnimMontage
        currFlipBookPage = -1;
        this.PlayingAnimationMontage = animMontage;
        flipBook = animMontage.getFlipBook();
        fpsPerImage = animMontage.getFpsPerImage();
    }


    @Override
    protected void RunFlipBook(float dt)
    {
        super.RunFlipBook(dt);
        if (PlayingAnimationMontage != null)
        {
            for(AnimNotify animNotify : PlayingAnimationMontage.getNotifies())
            {
                if(animNotify.getFrameStart() * fpsPerImage == currFlipBookFrame)
                {
                    animNotify.ReceiveNotifyBegin();
                }
                if(currFlipBookFrame >= animNotify.getFrameStart() * fpsPerImage &&
                        currFlipBookFrame <= animNotify.getFrameFinish() * fpsPerImage) animNotify.ReceiveNotifyTick();
                if(animNotify.getFrameFinish() * fpsPerImage == currFlipBookFrame) animNotify.ReceiveNotifyEnd();
            }
        }
        else return;
        if(!(currFlipBookPage + 1 < flipBook.length)) PlayingAnimationMontage = null;
    }

    protected void UpdateCurrentDirectionX(int AxisX)
    {
        vAxisX = AxisX;
        if (AxisX == 0)
        {
            if(directionList.size() <= 1) return;
            if(!directionList.contains(Direction.left) && !directionList.contains(Direction.right)) return;
            directionList.removeFirst();
            return;
        }
        Direction newDirection = (AxisX < 0 ) ? Direction.left : Direction.right;
        if(directionList.getLast() == newDirection) return;
        if(directionList.size() == 2) directionList.removeFirst();
        directionList.add(newDirection);
    }

    protected void UpdateCurrentDirectionY(int AxisY)
    {
        vAxisY = AxisY;
        if (AxisY == 0)
        {
            if(directionList.size() <= 1) return;
            if(!directionList.contains(Direction.up) && !directionList.contains(Direction.down)) return;
            directionList.removeFirst();
            return;
        }
        Direction newDirection = (AxisY < 0 ) ? Direction.down : Direction.up;
        if(directionList.getLast() == newDirection) return;
        if(directionList.size() == 2) directionList.removeFirst();
        directionList.add(newDirection);
    }

    public Direction GetCurrentDirection()
    {
        return directionList.getLast();
    }

    public void SetDirection(int directionX, int directionY)
    {
        UpdateCurrentDirectionX(directionX);
        UpdateCurrentDirectionY(directionY);
    }
}
