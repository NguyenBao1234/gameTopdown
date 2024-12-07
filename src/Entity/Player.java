package Entity;

import CoreGame.CollisionChecker;
import CoreGame.Enums.Collision;

import CoreGame.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.SoundComponent.SoundManager;
import Entity.Object.Master.BaseObject;
import Entity.Object.Master.InteractInterface;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Player extends BaseCharacter
{
    public final int screenX;
    public final int screenY;
    public float speedFactor = 1;
    private CollisionChecker collisionChecker;

    public Player()
    {
        //Setup Basic character property:
        flipBookArr = new BufferedImage[8][];
        setupAnimations();
        setAnimationToUse(0,4);
        worldX = 2*GamePanel.tileSize;
        worldY = 5* GamePanel.tileSize;
        screenX = GamePanel.screenWidth /2 - 64*GamePanel.scale/2;
        screenY = GamePanel.screenHeight /2 - 64*GamePanel.scale/2;

        collisionArea = new Rectangle();
        collisionArea.x = 2* GamePanel.scale;
        collisionArea.y = 17* GamePanel.scale;
        collisionArea.width = 11* GamePanel.scale;
        collisionArea.height = 5* GamePanel.scale;
        collisionMode = Collision.Block;
        collisionChecker = new CollisionChecker();
        SetupPlayerInputComponent();
    }

    private void SetupPlayerInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_E,true, this::Interact);
        ControllerComp.BindAction(KeyEvent.VK_P, true,this::PauseGame);
    }
    public void update(float DeltaTime)
    {
        InputAxisMove();
        handleLocationByCollision();
        runFlipBook(DeltaTime);
        handelAnimation();
        collisionChecker.getOverlappedObjects(this);

    }

    public void renderSprite(Graphics2D g2)
    {
        g2.drawImage(sprite,screenX,screenY, 64* GamePanel.scale,64* GamePanel.scale, null);
    }

    
    void InputAxisMove()
    {
        if( vAxisX !=0 && vAxisY !=0 ) speedFactor = 3/4f;

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

    void handleLocationByCollision()
    {
        if(collisionMode == Collision.NoCollision) return;
        collisionChecker.RespondToMap(this);
        if(bColliding)
        {
            switch(getCurrentDirection())
            {
                case down:
                    worldY -= (int) (speed * speedFactor);
                    break;
                case up:
                    worldY += (int) (speed * speedFactor);
                    break;
                case left:
                    worldX += (int) (speed * speedFactor);
                    break;
                case right:
                    worldX -= (int) (speed * speedFactor);
                    break;
            }
        }
    }
    void PauseGame() {
        if (KeyHandler.isKeyPressed(KeyEvent.VK_P)) {
            if (GamePanel.getInstGamePanel().gameState == GameState.Run)
            {
                GamePanel.getInstGamePanel().gameState = GameState.Pause;
            }
            else if (GamePanel.getInstGamePanel().gameState == GameState.Pause)
            {
                GamePanel.getInstGamePanel().gameState = GameState.Run;
            }
        }
    }
    /**Choose animation*/
    void handelAnimation()
    {
        switch (getCurrentDirection())
        {
            case up :
                //System.out.println("up");
                if(vAxisY == 0) setAnimationToUse(1,4);
                else setAnimationToUse(5,4);
                break;
            case down:
                //System.out.println("down");
                if(vAxisY == 0) setAnimationToUse(0,4);
                else setAnimationToUse(4,4);
                break;
            case left:
                //System.out.println("left");
                if(vAxisX == 0) setAnimationToUse(2,4);
                else setAnimationToUse(6,4);
                break;
            case right:
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

    void Pause()
    {
        System.out.println("Bind Function 'TestBindAction' called");
        SoundManager.playSound(1,false,"/Sound/SFX/coin.wav");
    }
    void Interact()
    {
        int BiasInteractBox = 4* GamePanel.scale;
        for(BaseObject overlappedObject : collisionChecker.getOverlappedObjectsInBox(worldX + collisionArea.x - BiasInteractBox,worldY + collisionArea.y - BiasInteractBox, collisionArea.width +BiasInteractBox*2,collisionArea.height + BiasInteractBox*2))
        {
            if(overlappedObject instanceof InteractInterface)
            {
                boolean interactSuccess = ((InteractInterface) overlappedObject).interact();
                System.out.println("Interact:"+interactSuccess);
            }
        }
    }
}
