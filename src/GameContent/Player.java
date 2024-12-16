package GameContent;

import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.EntityComponent.BaseCharacter;
import CoreGame.Data.Enums.Collision;
import CoreGame.Data.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.SoundComponent.SoundManager;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.WidgetComponent.HUD;

import GameContent.Object.InteractInterface;
import GameContent.WidgetInstances.PauseWD;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseCharacter
{
    public float speedFactor = 1;
    private final PauseWD pauseWD;

    public Player()
    {
        //Setup Basic character property:
        flipBookArr = new BufferedImage[8][];
        setupAnimations();
        setAnimationToUse(0,4);
        worldX = 0*GamePanel.tileSize;
        worldY = 0* GamePanel.tileSize;
        screenX = GamePanel.screenWidth /2 - 64*GamePanel.scale/2;
        screenY = GamePanel.screenHeight /2 - 64*GamePanel.scale/2 -16;
        SpriteRenderSizeX = 64*GamePanel.scale;
        SpriteRenderSizeY = 64*GamePanel.scale;

        collisionArea = new Rectangle();
        collisionArea.x = 2* GamePanel.scale;
        collisionArea.y = 13* GamePanel.scale;
        collisionArea.width = 11* GamePanel.scale;
        collisionArea.height = 5* GamePanel.scale;
        collisionMode = Collision.Block;

        SetupPlayerInputComponent();
        pauseWD = new PauseWD();
    }

    private void SetupPlayerInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_E,true, this::Interact);
        ControllerComp.BindAction(KeyEvent.VK_P, true,this::PauseGame);
    }

    @Override
    public void Tick(float delta) {
        super.Tick(delta);
        InputAxisMove();
        handleLocationByCollision();
        handelAnimation();
    }

    @Override
    public void Render(Graphics2D g2)
    {
        super.Render(g2);
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
        CollisionChecker.RespondToMap(this);
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

    void Interact()
    {
        int BiasInteractBox = 4* GamePanel.scale;
        for(BaseObject overlappedObject : CollisionChecker.getOverlappedObjectsInBox(worldX + collisionArea.x - BiasInteractBox,worldY + collisionArea.y - BiasInteractBox, collisionArea.width +BiasInteractBox*2,collisionArea.height + BiasInteractBox*2))
        {
            if(overlappedObject instanceof InteractInterface)
            {
                boolean interactSuccess = ((InteractInterface) overlappedObject).interact();
                System.out.println("Interact:"+interactSuccess);
            }
        }
    }

    void PauseGame()
    {
        if (GamePanel.GetInst().gameState == GameState.Run)
        {
            GamePanel.GetInst().gameState = GameState.Pause;
            SoundManager.playSound(1,false,"/Sound/SFX/coin.wav");
            HUD.AddWidget(pauseWD);
        }
        else if (GamePanel.GetInst().gameState == GameState.Pause)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(pauseWD);
            SoundManager.playSound(1,false,"/Sound/SFX/coin.wav");
        }
    }
}
