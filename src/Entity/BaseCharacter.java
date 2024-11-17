package Entity;

import CoreGame.Enums.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class BaseCharacter extends Entity
{
    protected int vAxisX;
    protected int vAxisY;

    private final ArrayList<Direction> directionList = new ArrayList<>(2);

    /**Mang chi can 'row' de chua bao nhieu flipBook (flipBook: buffered image array, tuong duong nhu 1 animation).
     * vi du: flipBookArr = new BufferImage[x][_].*/
    protected BufferedImage[][] flipBookArr;

    public BaseCharacter()
    {
        directionList.add(Direction.down);
    }
    /** Chon mang Buffered Image nao lam flipBook. Truoc khi goi ham nay can khoi tao flipBookArr[][]truoc*/
    public void setAnimationToUse(int index, int FPSPerImage)
    {
        if(index >= flipBookArr.length) return;
        flipBook = flipBookArr[index];
        fpsPerImage = FPSPerImage;
        //System.out.println("Setup Animation: successes");
    }


    protected void updateCurrentDirectionX(int AxisX)
    {
        vAxisX = AxisX;
        if (AxisX == 0)
        {
            if(directionList.size() <= 1) return;
            if(!directionList.contains(Direction.left) && !directionList.contains(Direction.right)) return;
            directionList.remove(Direction.left);
            directionList.remove(Direction.right);
        }
        Direction newDirection = (AxisX < 0 ) ? Direction.left : Direction.right;
        if(directionList.contains(newDirection)) return;
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
            directionList.remove(Direction.up);
            directionList.remove(Direction.down);
        }
        Direction newDirection = (AxisY < 0 ) ? Direction.up : Direction.down;
        if(directionList.contains(newDirection)) return;
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
