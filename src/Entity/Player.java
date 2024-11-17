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
    private final int screenX;
    private final int screenY;

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
            worldY -= speed * speedFactor;
        }
    }


    void handelAnimation()
    {
        switch (getCurrentDirection())
        {
            case Direction.up :
                if(vAxisY == 0) setAnimationToUse(1,4);
                else setAnimationToUse(4,4);
                break;
            case Direction.down:
                if(vAxisY == 0) setAnimationToUse(0,4);
                else setAnimationToUse(4,4);
                break;
            case Direction.left:
                if(vAxisX == 0) setAnimationToUse(2,4);
                else setAnimationToUse(4,4);
                break;
            case Direction.right:
                if(vAxisX == 0) setAnimationToUse(3,4);
                else setAnimationToUse(4,4);
                break;
        }

    }

    //Hardcode
    void setupAnimations()
    {
        BufferedImage fontIdleFlipBook[] =  ImageLoader.makeFlipBook("src/Resource/Player/front/idle");
        flipBookArr[0] = fontIdleFlipBook;

        BufferedImage backIdleFlipBook[] = ImageLoader.makeFlipBook("src/Resource/Player/back/idle");
        flipBookArr[1] = backIdleFlipBook;

        BufferedImage leftIdleFlipBook[] = ImageLoader.makeFlipBook("src/Resource/Player/left/idle");
        flipBookArr[2] = leftIdleFlipBook;

        BufferedImage rightIdleFlipBook[] = ImageLoader.makeFlipBook("src/Resource/Player/right/idle");
        flipBookArr[3] = rightIdleFlipBook;

        BufferedImage backWalkFlipBook[] = ImageLoader.makeFlipBook("src/Resource/Player/back/walk");
        flipBookArr[4] = backWalkFlipBook;
    }
}
