package GameContent;

import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.Data.Enums.Collision;
import CoreGame.Data.Enums.GameState;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.PlayerComponent.Player;
import CoreGame.SoundComponent.SoundManager;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.InteractInterface;
import GameContent.WidgetInstances.PauseWD;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MainPlayer extends Player
{
    public float speedFactor = 1;
    private final PauseWD pauseWD;

    public MainPlayer()
    {
        //Setup Basic character property:
        flipBookArr = new BufferedImage[8][];
        SetupAnimations();
        SetAnimationToUse(0,4);
        worldX = 0* GamePanel.tileSize;
        worldY = 0* GamePanel.tileSize;

        SpriteRenderSizeX = 64*GamePanel.scale;
        SpriteRenderSizeY = 64*GamePanel.scale;

        screenX = GamePanel.screenWidth /2 - 64*GamePanel.scale/2;
        screenY = GamePanel.screenHeight /2 - 64*GamePanel.scale/2 -16;

        CollisionArea.x = 2* GamePanel.scale;
        CollisionArea.y = 13* GamePanel.scale;
        CollisionArea.width = 11* GamePanel.scale;
        CollisionArea.height = 5* GamePanel.scale;
        CollisionMode = Collision.Block;

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
        else speedFactor = 1;

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_A) && !KeyHandler.isKeyPressed(KeyEvent.VK_D)) UpdateCurrentDirectionX(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_A))
        {
            UpdateCurrentDirectionX(-1);
            worldX -= (int) (Speed * speedFactor);
        }

        if(KeyHandler.isKeyPressed(KeyEvent.VK_D))
        {
            UpdateCurrentDirectionX(1);
            worldX += (int) (Speed * speedFactor);
        }

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_W) && !KeyHandler.isKeyPressed(KeyEvent.VK_S)) UpdateCurrentDirectionY(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_S))
        {
            UpdateCurrentDirectionY(-1);
            worldY += (int) (Speed * speedFactor); // Y tang = di xuong duoi man hinh
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_W))
        {
            UpdateCurrentDirectionY(1);
            worldY -= (int)(Speed * speedFactor);
        }
    }

    void handleLocationByCollision()
    {
        if(CollisionMode == Collision.NoCollision) return;
        CollisionChecker.RespondToMap(this);
        if(bColliding)
        {
            switch(GetCurrentDirection())
            {
                case down:
                    worldY -= (int) (Speed * speedFactor);
                    break;
                case up:
                    worldY += (int) (Speed * speedFactor);
                    break;
                case left:
                    worldX += (int) (Speed * speedFactor);
                    break;
                case right:
                    worldX -= (int) (Speed * speedFactor);
                    break;
            }
        }
    }

    /**Choose animation*/
    void handelAnimation()
    {
        switch (GetCurrentDirection())
        {
            case up :
                //System.out.println("up");
                if(vAxisY == 0) SetAnimationToUse(1,4);
                else SetAnimationToUse(5,4);
                break;
            case down:
                //System.out.println("down");
                if(vAxisY == 0) SetAnimationToUse(0,4);
                else SetAnimationToUse(4,4);
                break;
            case left:
                //System.out.println("left");
                if(vAxisX == 0) SetAnimationToUse(2,4);
                else SetAnimationToUse(6,4);
                break;
            case right:
                //System.out.println("right");
                if(vAxisX == 0) SetAnimationToUse(3,4);
                else SetAnimationToUse(7,4);
                break;
        }
    }

    //Hardcode
    void SetupAnimations()
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
        int BiasInteractBox = 8* GamePanel.scale;
        for(BaseObject overlappedObject : CollisionChecker.getOverlappedObjectsInBox(worldX + CollisionArea.x - BiasInteractBox,worldY + CollisionArea.y - BiasInteractBox, CollisionArea.width +BiasInteractBox*2, CollisionArea.height + BiasInteractBox*2))
        {
            if(overlappedObject instanceof InteractInterface)
            {
                ((InteractInterface) overlappedObject).interact();
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

    private void Attack()
    {

    }
}
