package GameContent.NPC.Enemy;

import CoreGame.AnimationClass.AnimMontage;
import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import GameContent.NPC.BaseCharacterPendOnPlayer;
import GameContent.NotifyInstances.NotifyWithBinder;
import GameContent.NotifyInstances.TraceDamageNotify;
import HelpDevGameTool.ImageUtility;

public abstract class Enemy extends BaseCharacterPendOnPlayer
{
    protected float Damage = 5;
    protected float health = 40;

    protected boolean bCanAttack = false;
    protected int AttackBoxScaleX = 1, AttackBoxScaleY = 2;
    protected int AttackBoxWidth =48, AttackBoxHeight = 48;
    protected TraceDamageNotify damageNotify= new TraceDamageNotify(0, this, AttackBoxScaleX, AttackBoxScaleY);
    protected String AttackMontageSrc[] = new String[4];
    protected AnimMontage AttackMontage = new AnimMontage(damageNotify);

    protected NotifyWithBinder DeathNotify = new NotifyWithBinder(5,0,this::DestroyThis);
    protected AnimMontage DeathMontage = new AnimMontage(DeathNotify);

    protected String OnHitMontageSrc[] = new String[4];
    protected AnimMontage OnHitMontage;

    protected int velocityDirection = 1;

    @Override
    public void Tick(float delta) {
        super.Tick(delta);
        HandleLocomotion();
        HandleAbleAttack();
        HandleAIMoving();
    }

    private void HandleAbleAttack()
    {
        int BoxWorldX = worldX;
        int BoxWorldY = worldY;
        int BiasX = AttackBoxScaleX * GamePanel.tileSize - GamePanel.tileSize;
        int BiasY = AttackBoxScaleY * GamePanel.tileSize - GamePanel.tileSize;
        boolean bHasSwapAttackBoxEdge = false;
        switch (GetCurrentDirection())
        {
            case up :
                BoxWorldY -= GamePanel.tileSize - BiasY;
                BoxWorldX -= BiasX/2;
                break;
            case down:
                BoxWorldY += GamePanel.tileSize;
                BoxWorldX -= BiasX/2;
                break;
            case left:
                SwapBoxDirect();
                bHasSwapAttackBoxEdge = true;
                BoxWorldX -= GamePanel.tileSize - BiasY;
                BoxWorldY -= BiasX/2;
                break;
            case right:
                SwapBoxDirect();
                bHasSwapAttackBoxEdge = true;
                BoxWorldX += GamePanel.tileSize;
                BoxWorldY -= BiasX/2;
                break;
        }
        boolean DecideAttack = false;
        for (BaseObject object: CollisionChecker.GetOverlappedObjectsInBox(BoxWorldX, BoxWorldY, AttackBoxWidth, AttackBoxHeight))
        {
            if(object instanceof MainPlayer) DecideAttack = true;
        }
        bCanAttack = DecideAttack;
        if(bHasSwapAttackBoxEdge) SwapBoxDirect();
    }
    private void SwapBoxDirect()
    {
        AttackBoxWidth = AttackBoxWidth + AttackBoxHeight;
        AttackBoxHeight = AttackBoxWidth - AttackBoxHeight;
        AttackBoxWidth = AttackBoxWidth - AttackBoxHeight;
    }

    //Handle Moving by VelocityAxis
    protected void HandleAIMoving()
    {
        if(PlayingAnimationMontage != null)return;
        if(vAxisX == 0 && vAxisY == 0) return;
        int collX = worldX + CollisionArea.x;
        int collY = worldY + CollisionArea.y;
        if (vAxisY > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY -= Speed;
            }
        }
        if (vAxisY < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY += Speed;
            }
        }
        if (vAxisX > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX += Speed;
            }
        }
        if (vAxisX < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX -= Speed;
            }
        }
    }

    abstract protected void HandleLocomotion();

    @Override
    protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY)
    {
        if(health <=0) return;
        health -= Damage;
        if(health > 0) OnReceiveDamage();
        else OnDeath();
    }

    protected void Attack()
    {
        if(PlayingAnimationMontage != null) return;
        switch (GetCurrentDirection())
        {
            case down :AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[0],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case up:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[1],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case left:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[2],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case right:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[3],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
        }
        PlayAnimMontage(AttackMontage);
    }
    protected void OnReceiveDamage()
    {
        if(PlayingAnimationMontage != null) return;
        if(OnHitMontage == null) return;
        switch (GetCurrentDirection())
        {
            case up :OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[0],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case down:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[1],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case left:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[2],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
            case right:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[3],SpriteRenderSizeX,SpriteRenderSizeY));
                break;
        }
        PlayAnimMontage(OnHitMontage);
    };
    abstract protected void OnDeath();

    private void DestroyThis(){Destroy(this);}

    public float getDamage() {return Damage;}

    public void setDamage(float damage) {Damage = damage;}

    public float getHealth() {return health;}

    public void setHealth(float health) {
        this.health = health;}

}
