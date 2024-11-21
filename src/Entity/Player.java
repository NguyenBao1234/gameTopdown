package Entity;

import CoreGame.Enums.Direction;
import CoreGame.GamePanel;
import CoreGame.KeyHandler;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseCharacter
{
    GamePanel gamePanel;
    public final int screenX;  //private to public
    public final int screenY;  //private to public

    public Player(GamePanel gamePanel)
    {
        //Setup Basic character property:
        flipBookArr = new BufferedImage[8][];
        setupAnimations();
        setAnimationToUse(0,4);

        this.gamePanel = gamePanel;
        screenX = gamePanel.screenWidth/2 - 64*3/2;
        screenY = gamePanel.screenHeight/2 - 64*3/2;

        collisionArea = new Rectangle(8,16,32,32);

    }

    public void update(float DeltaTime)
    {
        InputAxisFlow();
        runFlipBook(DeltaTime);
        handelAnimation();
    }

    public void renderSprite(Graphics2D g2)
    {
        g2.drawImage(sprite,screenX,screenY, 64*gamePanel.scale,64*gamePanel.scale, null);
    }

    
    void InputAxisFlow()
    {
        float speedFactor = 1;
        if( vAxisX !=0 && vAxisY !=0 ) speedFactor =  0.5f;

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_A) && !KeyHandler.isKeyPressed(KeyEvent.VK_D)) updateCurrentDirectionX(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_A))
        {
            updateCurrentDirectionX(-1);
            worldX -= (int) (speed * speedFactor);
        }

        if(KeyHandler.isKeyPressed(KeyEvent.VK_D))
        {
            updateCurrentDirectionX(1);
            worldX += (int) (speed * speedFactor);
        }

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_W) && !KeyHandler.isKeyPressed(KeyEvent.VK_S)) updateCurrentDirectionY(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_S))
        {
            updateCurrentDirectionY(-1);
            worldY += (int) (speed * speedFactor); // Y tang = di xuong duoi man hinh
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_W))
        {
            updateCurrentDirectionY(1);
            worldY -= (int)(speed * speedFactor);
        }
    }

    /**Choose animation*/
    void handelAnimation()
    {
        switch (getCurrentDirection())
        {
            case Direction.up :
                //System.out.println("up");
                if(vAxisY == 0) setAnimationToUse(1,4);
                else setAnimationToUse(5,4);
                break;
            case Direction.down:
                //System.out.println("down");
                if(vAxisY == 0) setAnimationToUse(0,4);
                else setAnimationToUse(4,4);
                break;
            case Direction.left:
                //System.out.println("left");
                if(vAxisX == 0) setAnimationToUse(2,4);
                else setAnimationToUse(6,4);
                break;
            case Direction.right:
                //System.out.println("right");
                if(vAxisX == 0) setAnimationToUse(3,4);
                else setAnimationToUse(7,4);
                break;
        }

    }

    //Hardcode
    void setupAnimations()
    {
        flipBookArr[0] = ImageLoader.makeFlipBook("/Player/front/idle");

        flipBookArr[1] = ImageLoader.makeFlipBook("/Player/back/idle");

        flipBookArr[2] = ImageLoader.makeFlipBook("/Player/left/idle");

        flipBookArr[3] = ImageLoader.makeFlipBook("/Player/right/idle");

        flipBookArr[4] = ImageLoader.makeFlipBook("/Player/front/walk");

        flipBookArr[5] = ImageLoader.makeFlipBook("/Player/back/walk");

        flipBookArr[6] = ImageLoader.makeFlipBook("/Player/left/walk");

        flipBookArr[7] = ImageLoader.makeFlipBook("/Player/right/walk");
    }
}
