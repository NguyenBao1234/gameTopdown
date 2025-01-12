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
import GameContent.NotifyInstances.NotifyWithBinder;
import GameContent.NotifyInstances.TraceDamageNotify;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.WidgetInstances.GameOverWD;
import GameContent.WidgetInstances.HealthBar;
import GameContent.WidgetInstances.PauseWD;
import HelpDevGameTool.ImageUtility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MainPlayer extends Player
{
    public float currentSpeedFactor = 1.0f;
    public float speedFactor = 1.0f;
    private final PauseWD pauseWD = new PauseWD();
    private final HealthBar StateWD = new HealthBar(100, 22);
    private final GameOverWD GameOverScreen = new GameOverWD();
    private float DamageWeapon = 4;
    public float maxhealth = 100;
    public float currenthealth = 100;
    private boolean bFreeToControl = true;

    private NotifyWithBinder OnDeathNotify = new NotifyWithBinder(7,7);
    private final TraceDamageNotify DmgNotify = new TraceDamageNotify(1,this,2,1);
    private final AnimMontage AttackMontage = new AnimMontage(4,DmgNotify);
    private final AnimMontage DeathMontage = new AnimMontage(4,ImageUtility.MakeFlipBookFromSheet("/Player/spr_player_death.png",64,64),OnDeathNotify);
    private final AnimMontage OnHitMontage = new AnimMontage(3);

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
        OnDeathNotify.AddDynamic(this::OnGameOver);
    }

    private void SetupPlayerInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_E,true, this::Interact);
        ControllerComp.BindAction(KeyEvent.VK_P, true,this::PauseGame);
        ControllerComp.BindAction(KeyEvent.VK_J,true,this::Attack);
        ControllerComp.BindAction(KeyEvent.VK_SHIFT,true,this::Dash);
        ControllerComp.BindAction(KeyEvent.VK_T,true,this::ToggleState);
        ControllerComp.BindAction(KeyEvent.VK_K,true,this::Dame);
    }

    @Override
    public void Tick(float delta) {
        super.Tick(delta);
        InputAxisMove();
        handleLocation();
        handelAnimation();
    }

    @Override
    public void Render(Graphics2D g2)
    {
        super.Render(g2);
    }


    void InputAxisMove()
    {
        if(currenthealth<=0) return;
        if(!bFreeToControl) return;

        float diagonalFactor = (vAxisX != 0 && vAxisY != 0) ? 0.75f : 1.0f;

        // Combine base speed (affected by swamp) with diagonal movement
        speedFactor = currentSpeedFactor * diagonalFactor;

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

    public void setCurrentSpeedFactor(float factor) {
        this.currentSpeedFactor = factor;
    }

    void handleLocation()
    {
        if(vAxisX == 0 && vAxisY == 0) return;
        int collX = worldX + CollisionArea.x;
        int collY = worldY + CollisionArea.y;
        if (vAxisY > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this, collX, collY - Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY -= (int) (Speed * speedFactor);
            }
        }
        if (vAxisY < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this, collX, collY + Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY += (int) (Speed * speedFactor);
            }
        }
        if (vAxisX > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this,collX + Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX += (int) (Speed * speedFactor);
            }
        }
        if (vAxisX < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this, collX - Speed, collY, CollisionArea.width, CollisionArea.height))
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
        for(BaseObject overlappedObject : CollisionChecker.GetOverlappedObjectsInBox(
                this,worldX + CollisionArea.x - BiasInteractBox,
                worldY + CollisionArea.y - BiasInteractBox,
                CollisionArea.width +BiasInteractBox*2,
                CollisionArea.height + BiasInteractBox*2))
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
            SoundUtility.playSound(1,false, "/Sound/SFX/Object/coin.wav");
            HUD.AddWidget(pauseWD);
        }
        else if (GamePanel.GetInst().gameState == GameState.Pause)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(pauseWD);
            SoundUtility.playSound(1,false, "/Sound/SFX/Object/coin.wav");
        }
    }

    private void Attack()
    {
        if(PlayingAnimationMontage != null || !bFreeToControl) return;
        SoundUtility.playSound(1,false, "/Sound/SFX/Object/SwordWhoose.wav");
        switch (GetCurrentDirection())
        {
            case up :
                DmgNotify.setFrameStart(1);
                AttackMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/back/attack"));
                break;
            case down:
                DmgNotify.setFrameStart(1);
                AttackMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/front/attack"));
                break;
            case left:
                DmgNotify.setFrameStart(1);
                AttackMontage.setFlipBook( ImageUtility.makeFlipBook("/Player/left/attack"));
                break;
            case right:
                DmgNotify.setFrameStart(5);
                AttackMontage.setFlipBook( ImageUtility.makeFlipBook("/Player/right/attack"));
                break;
        }
        PlayAnimMontage(AttackMontage);
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

    private void ToggleState()
    {
        if (GamePanel.GetInst().gameState != GameState.Run) return;
        if(StateWD.IsOnScreen()) HUD.RemoveWidget(StateWD);
        else HUD.AddWidget(StateWD);
    }

    void Dame(){
        ApplyPointDamage(this,null,5,0,0,0,0);
    }

    @Override
    protected void OnAnyDamage(Entity Causer, float Damage, int SourceWorldX, int SourceWorldY) {
        currenthealth -= Damage;
        StateWD.updateHealth(currenthealth);
        if (Damage < 0 ) return;
        if(currenthealth <= 0) DeathAnim();
        String SoundOnDamageSrc;
        int randomNum = new Random().nextInt(0,6);
        if(randomNum>2) SoundOnDamageSrc = "/Sound/SFX/Voice/Player/FemaleReceiveDamage1.wav";
        else SoundOnDamageSrc = "/Sound/SFX/Voice/Player/FemaleReceiveDamage.wav";

        SoundUtility.playSound(1, false,SoundOnDamageSrc);
        if(currenthealth > 0) ReceiveDamageAnim();
        else DeathAnim();
    }

    private void ReceiveDamageAnim()
    {
        switch (GetCurrentDirection())
        {
            case up:OnHitMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/back/hit"));
                break;
            case down:OnHitMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/front/hit"));
                break;
            case left:OnHitMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/left/hit"));
                break;
            case right:OnHitMontage.setFlipBook(ImageUtility.makeFlipBook("/Player/right/hit"));
                break;
        }
        PlayAnimMontage(OnHitMontage);
    }

    private void DeathAnim()
    {
        CollisionMode = Collision.NoCollision;
        if(StateWD.IsOnScreen()) HUD.RemoveWidget(StateWD);
        PlayAnimMontage(DeathMontage);
    }

    private void OnGameOver()
    {
        GamePanel.GetInst().gameState = GameState.Pause;
        if(GameOverScreen.IsOnScreen()) return;
        HUD.AddWidget(GameOverScreen);
    }

    public float getDamageWeapon() {
        return DamageWeapon;
    }

    public void setDamageWeapon(float damageWeapon) {
        DamageWeapon = damageWeapon;
    }



    public void setCurrentHealth(float health) {
        this.currenthealth = health;
    }
    public float getMaxHealth() {
        return maxhealth;
    }

    public float getCurrentHealth() {
        return currenthealth;
    }

    public void SetFreeToControl(boolean bCanControl)
    {
        bFreeToControl = bCanControl;
        UpdateCurrentDirectionX(0);
        UpdateCurrentDirectionY(0);
    }

    public void UpdateStateWD()
    {
        StateWD.updateHealth(currenthealth);
    }
}
