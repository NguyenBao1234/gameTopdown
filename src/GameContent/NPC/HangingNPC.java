package GameContent.NPC;

import HelpDevGameTool.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.Random;

public class HangingNPC extends BaseCharacterPendOnPlayer
{
    private final Random random = new Random();
    public HangingNPC()
    {
        SetupAnimations();
        SpriteRenderSizeX = 64;
        SpriteRenderSizeY = 64;
    }
    @Override
    public void Tick(float delta)
    {
        super.Tick(delta);
        handelAnimation();
        SimulateMoving();
    }

    private void SetupAnimations()
    {
        flipBookArr = new BufferedImage[4][];
        flipBookArr[0] = ImageLoader.makeFlipBook("/Slime/Slime1/Back/Walk");
        flipBookArr[1] = ImageLoader.makeFlipBook("/Slime/Slime1/Front/Walk");
        flipBookArr[2] = ImageLoader.makeFlipBook("/Slime/Slime1/Left/Walk");
        flipBookArr[3] = ImageLoader.makeFlipBook("/Slime/Slime1/Right/Walk");
    }

    void SimulateMoving()
    {
        switch (random.nextInt(3))
        {
            case 0:
                worldX += speed;
                UpdateCurrentDirectionX(1);
                break;
            case 1:
                worldX -= speed;
                UpdateCurrentDirectionX(-1);
                break;
            case 2:
                worldY += speed;
                UpdateCurrentDirectionY(-1);
                break;
            case 3:
                worldY += speed;
                UpdateCurrentDirectionY(1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        }
    }

    void handelAnimation()
    {
        switch (GetCurrentDirection())
        {
            case up :
                //System.out.println("up");
                //if(vAxisY == 0) setAnimationToUse(1,4);
                setAnimationToUse(0,4);
                break;
            case down:
                //System.out.println("down");
                //if(vAxisY == 0) setAnimationToUse(0,4);
                setAnimationToUse(1,4);
                break;
            case left:
                //System.out.println("left");
                //if(vAxisX == 0) setAnimationToUse(2,4);
                setAnimationToUse(2,4);
                break;
            case right:
                //System.out.println("right");
                //if(vAxisX == 0) setAnimationToUse(3,4);
                setAnimationToUse(3,4);
                break;
        }
    }
}
