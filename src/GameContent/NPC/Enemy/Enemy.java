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
    protected float AttackBoxScaleX = 1, AttackBoxScaleY = 1;
    protected int AttackBoxWidth, AttackBoxHeight;
    protected TraceDamageNotify damageNotify= new TraceDamageNotify(0, this, (int)AttackBoxScaleX, (int)AttackBoxScaleY);
    protected String AttackMontageSrc[] = new String[4];
    protected AnimMontage AttackMontage = new AnimMontage(6,damageNotify);

    protected NotifyWithBinder DeathNotify = new NotifyWithBinder(5,0,this::DestroyThis);
    protected AnimMontage DeathMontage = new AnimMontage(DeathNotify);

    protected String OnHitMontageSrc[] = new String[4];
    protected AnimMontage OnHitMontage = new AnimMontage();

    protected int velocityDirection = 1;

    @Override
    public void Tick(float delta) {
        super.Tick(delta);
        HandleLocomotion();
        HandleAbleAttack();
        if(bCanAttack)Attack();
        HandleAIMoving();
    }

    private void HandleAbleAttack()
    {
        int BoxWorldX = worldX;
        int BoxWorldY = worldY;
        AttackBoxWidth = (int)(AttackBoxScaleX * GamePanel.tileSize);
        AttackBoxHeight = (int)(AttackBoxScaleY * GamePanel.tileSize);

        int BiasX = AttackBoxWidth - GamePanel.tileSize;
        int BiasY = AttackBoxHeight - GamePanel.tileSize;
        boolean bHasSwapAttackBoxEdge = false;
        switch (GetCurrentDirection())
        {
            case up :
                BoxWorldY -= AttackBoxWidth;
                break;
            case down:
                break;
            case left:
                SwapBoxDirect();
                bHasSwapAttackBoxEdge = true;
                BoxWorldX -= 0;
                BoxWorldY -= BiasX/2;
                break;
            case right:
                SwapBoxDirect();
                bHasSwapAttackBoxEdge = true;
                BoxWorldX += 0;
                BoxWorldY -= BiasX/2;
                break;
        }
        boolean DecideAttack = false;
        for (BaseObject object: CollisionChecker.GetOverlappedObjectsInBox(this, BoxWorldX, BoxWorldY,
                AttackBoxWidth, AttackBoxHeight))
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
    protected boolean HandleAIMoving()
    {
        if(bCanAttack) return false;
        if(PlayingAnimationMontage != null)return false;
        if(vAxisX == 0 && vAxisY == 0) return false;

        int collX = worldX + CollisionArea.x;
        int collY = worldY + CollisionArea.y;
        if (vAxisY > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this, collX, collY - Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY -= Speed;
                return true;
            }
        }
        if (vAxisY < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this,collX, collY + Speed, CollisionArea.width, CollisionArea.height))
            {
                worldY += Speed;
                return true;
            }
        }
        if (vAxisX > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this,collX + Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX += Speed;
                return true;
            }
        }
        if (vAxisX < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(this,collX - Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                worldX -= Speed;
                return true;
            }
        }
        return false;
    }

    abstract protected void HandleLocomotion();

    @Override
    protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY)
    {
        if(health<=0)
        {
            OnDeath();
            return;
        }
        health -= Damage;
        if(health > 0) OnReceiveDamage();
        else OnDeath();
    }

    protected void Attack()
    {
        if(health<=0) return;
        if(PlayingAnimationMontage != null) return;
        switch (GetCurrentDirection())
        {
            case down :AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[0],flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case up:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[1],flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case left:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[2],flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case right:AttackMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(AttackMontageSrc[3],flipBookFrameSizeX,flipBookFrameSizeY));
                break;
        }
        PlayAnimMontage(AttackMontage);
    }
    protected void OnReceiveDamage()
    {
        if(OnHitMontage == null) return;
        switch (GetCurrentDirection())
        {
            case up :OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[0], flipBookFrameSizeX, flipBookFrameSizeY));
                break;
            case down:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[1],flipBookFrameSizeX, flipBookFrameSizeY));
                break;
            case left:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[2],flipBookFrameSizeX, flipBookFrameSizeY));
                break;
            case right:OnHitMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet(OnHitMontageSrc[3],flipBookFrameSizeX, flipBookFrameSizeY));
                break;
        }
        PlayAnimMontage(OnHitMontage);
    };
    abstract protected void OnDeath();

    private void DestroyThis(){Destroy(this);
    System.out.println("Destroyed Enemy");}

    public float getDamage() {return Damage;}

    public void setDamage(float damage) {Damage = damage;}

    public float getHealth() {return health;}

    public void setHealth(float health) {
        this.health = health;}

}
