package CoreGame.EntityComponent;

import CoreGame.AnimNotifyComponent.BaseAnimNotify;
import CoreGame.Data.Enums.Direction;
import CoreGame.GamePanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class BaseCharacter extends BaseObject
{
    protected int vAxisX;
    protected int vAxisY;
    protected int speed = 4;

    private boolean bPlayingMontage;
    private BaseAnimNotify animNotify;

    private static final ArrayList<Direction> directionList = new ArrayList<>(2);

    public int getSpeed(){return speed;}

    /**Mang chi can 'row' de chua bao nhieu flipBook (flipBook: buffered image array, tuong duong nhu 1 animation).
     * vi du: flipBookArr = new BufferImage[x][_].*/
    protected BufferedImage[][] flipBookArr;

    public BaseCharacter()
    {
        directionList.add(Direction.down);
    }

    @Override
    public void Tick(float delta) {
        runFlipBook(delta);
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }

    /** Chon mang Buffered Image nao lam flipBook. Truoc khi goi ham nay can khoi tao flipBookArr[][]truoc*/
    public void setAnimationToUse(int index, int FPSPerImage)
    {
        if(index > flipBookArr.length-1) return;
        if(bPlayingMontage) return;
        flipBook = flipBookArr[index];
        fpsPerImage = FPSPerImage;
        //System.out.println("Setup Animation: successes");
    }

    /**Play the AnimMontage once. After that, back to the normal animation state */
    public void playAnimMontage(BufferedImage[] AnimMontage, int FPSPerImage)
    {
        bPlayingMontage = true;
        currentFrame = -1;//ensure playing Full AnimMontage
        flipBook = AnimMontage;
        fpsPerImage = FPSPerImage;
    }

    /**Play the AnimMontage once. After that, back to the normal animation state.
     * While playing Anim, execute notify function */
    public void playAnimMontageWithNotify(BufferedImage[] AnimMontage, int FPSPerImage, BaseAnimNotify notify)
    {
        bPlayingMontage = true;
        currentFrame = -1;//ensure playing Full AnimMontage
        flipBook = AnimMontage;
        fpsPerImage = FPSPerImage;
        animNotify =  notify;
    }

    @Override
    protected void runFlipBook(float dt)
    {
        super.runFlipBook(dt);
        if(!bPlayingMontage) return;
        if (animNotify != null)
        {
            animNotify.ReceiveNotifyTick();
            if(animNotify.getFrameStart() == currentFrame) animNotify.ReceiveNotifyBegin();
            if(animNotify.getFrameFinish() == currentFrame) animNotify.ReceiveNotifyEnd();
        }
        if(currentFrame + 1 >= flipBook.length) bPlayingMontage = false;
    }

    protected void updateCurrentDirectionX(int AxisX)
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

    protected void updateCurrentDirectionY(int AxisY)
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

    public Direction getCurrentDirection()
    {
        return directionList.getLast();
    }

    public void setDirection(int directionX, int directionY)
    {
        updateCurrentDirectionX(directionX);
        updateCurrentDirectionY(directionY);
    }
}
