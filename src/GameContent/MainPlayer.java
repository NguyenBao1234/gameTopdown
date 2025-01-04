package GameContent;

import CoreGame.AnimationClass.AnimMontage;
import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.Data.Enums.Collision;
import CoreGame.Data.Enums.GameState;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.PlayerComponent.Player;
import CoreGame.SoundComponent.SoundUtility;
import CoreGame.WidgetComponent.HUD;
import GameContent.NotifyInstances.TraceDamageNotify;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.WidgetInstances.PauseWD;
import HelpDevGameTool.ImageUtility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MainPlayer extends Player
{
    public float speedFactor = 1;
    private final PauseWD pauseWD = new PauseWD();
    private float DamageWeapon = 4;
    private float health = 100;
    private boolean bFreeToControl = true;
    private TraceDamageNotify DmgNotify = new TraceDamageNotify(1,this,2,1);
    private final AnimMontage AttackMontage = new AnimMontage();

    public MainPlayer()
    {
        //Setup Basic character property:
        flipBookArr = new BufferedImage[8][];
        SetupAnimations();
        SetAnimationToUse(0,4);
        worldX = 5* GamePanel.tileSize;
        worldY = 12* GamePanel.tileSize;

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
        AttackMontage.AddNotify(DmgNotify);
    }

    private void SetupPlayerInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_E,true, this::Interact);
        ControllerComp.BindAction(KeyEvent.VK_P, true,this::PauseGame);
        ControllerComp.BindAction(KeyEvent.VK_J,true,this::Attack);
        ControllerComp.BindAction(KeyEvent.VK_SHIFT,true,this::Dash);
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
        if(!bFreeToControl) return;
        if( vAxisX !=0 && vAxisY !=0 ) speedFactor = 3/4f;
        else speedFactor = 1;

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_A) && !KeyHandler.isKeyPressed(KeyEvent.VK_D)) UpdateCurrentDirectionX(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_A))
        {
            UpdateCurrentDirectionX(-1);
        }

        if(KeyHandler.isKeyPressed(KeyEvent.VK_D))
        {
            UpdateCurrentDirectionX(1);
        }

        if (!KeyHandler.isKeyPressed(KeyEvent.VK_W) && !KeyHandler.isKeyPressed(KeyEvent.VK_S)) UpdateCurrentDirectionY(0);

        if(KeyHandler.isKeyPressed(KeyEvent.VK_S))
        {
            UpdateCurrentDirectionY(-1);
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_W))
        {
            UpdateCurrentDirectionY(1);
        }
    }

    void handleLocationByCollision()
    {
        if(vAxisX == 0 && vAxisY == 0) return;
        int collX = worldX + CollisionArea.x;
        int collY = worldY + CollisionArea.y;
        if (vAxisY > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY -= (int) (Speed * speedFactor);
            }
        }
        if (vAxisY < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY += (int) (Speed * speedFactor);
            }
        }
        if (vAxisX > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX += (int) (Speed * speedFactor);
            }
        }
        if (vAxisX < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX -= (int) (Speed * speedFactor);
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
        flipBookArr[0] = ImageUtility.makeFlipBook("/Player/front/idle");

        flipBookArr[1] = ImageUtility.makeFlipBook("/Player/back/idle");

        flipBookArr[2] = ImageUtility.makeFlipBook("/Player/left/idle");

        flipBookArr[3] = ImageUtility.makeFlipBook("/Player/right/idle");

        flipBookArr[4] = ImageUtility.makeFlipBook("/Player/front/walk");

        flipBookArr[5] = ImageUtility.makeFlipBook("/Player/back/walk");

        flipBookArr[6] = ImageUtility.makeFlipBook("/Player/left/walk");

        flipBookArr[7] = ImageUtility.makeFlipBook("/Player/right/walk");
    }

    void Interact()
    {
        int BiasInteractBox = 8* GamePanel.scale;
        for(BaseObject overlappedObject : CollisionChecker.GetOverlappedObjectsInBox(worldX + CollisionArea.x - BiasInteractBox,worldY + CollisionArea.y - BiasInteractBox, CollisionArea.width +BiasInteractBox*2, CollisionArea.height + BiasInteractBox*2))
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
            SoundUtility.playSound(1,false,"/Sound/SFX/coin.wav");
            HUD.AddWidget(pauseWD);
        }
        else if (GamePanel.GetInst().gameState == GameState.Pause)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(pauseWD);
            SoundUtility.playSound(1,false,"/Sound/SFX/coin.wav");
        }
    }

    private void Attack()
    {
        if(animMontage != null || !bFreeToControl) return;
        SoundUtility.playSound(1,false,"/Sound/SFX/SwordWhoose.wav");
        switch (GetCurrentDirection())
        {
            case up :
                DmgNotify.setFrameStart(0);
                AttackMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/back/attack"));
                break;
            case down:
                DmgNotify.setFrameStart(0);
                AttackMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/front/attack"));
                break;
            case left:
                DmgNotify.setFrameStart(0);
                AttackMontage.setFlipBook( ImageUtility.makeFlipBook("/Player/left/attack"));
                break;
            case right:
                DmgNotify.setFrameStart(5);
                AttackMontage.setFlipBook( ImageUtility.makeFlipBook("/Player/right/attack"));
                break;
        }
        PlayAnimMontage(AttackMontage, 4);
    }

    private void Dash()
    {
        if(!bFreeToControl) return;
        switch (GetCurrentDirection())
        {
            case up: worldY -= 6 * Speed;
                break;
            case down: worldY += 6 * Speed;
                break;
            case left:
                worldX -= 6 * Speed;
                break;
            case right:
                worldX += 6 * Speed;
                break;
        }
    }

    @Override
    protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY) {
        health -= Damage;
        if(health > 0) ReceiveDamageAnim();
        else DeathAnim();
    }

    private void ReceiveDamageAnim()
    {
        switch (GetCurrentDirection())
        {
            case up:
                break;
            case down:
                break;
            case left:
                break;
            case right:
                break;
        }
    }

    private void DeathAnim()
    {
        switch (GetCurrentDirection())
        {
            case up:
                break;
            case down:
                break;
            case left:
                break;
            case right:
                break;
        }
    }

    public float getDamageWeapon() {
        return DamageWeapon;
    }

    public void setDamageWeapon(float damageWeapon) {
        DamageWeapon = damageWeapon;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void SetFreeToControl(boolean bCanControl) {
        bFreeToControl = bCanControl;}
}
