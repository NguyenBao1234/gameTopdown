package CoreGame.EntityComponent;

import CoreGame.Data.Enums.Collision;
import CoreGame.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class BaseObject extends Entity
{
    /**flipbook contain buffer images to make frame by frame animation. FlipBook=Animation*/
    protected BufferedImage[] flipBook;
    protected BufferedImage sprite;

    protected boolean bVisible = true;
    protected int screenX,screenY;
    protected int SpriteRenderSizeX, SpriteRenderSizeY;

    protected float passDelta = 0;
    protected int currentFrame = -1;
    protected int fpsPerImage = 4;

    /**Refresh sprite frequently to run flipBook (or play animation)*/
    protected void RunFlipBook(float dt)
    {
        if(flipBook == null||flipBook.length < 2) return;
        passDelta += dt;
        if(passDelta < dt * fpsPerImage) return;
        //refresh new sprite by new frame of flipBook
        passDelta -= dt* fpsPerImage;
        currentFrame ++;
        if(currentFrame >= flipBook.length) currentFrame = 0;
        sprite = flipBook[currentFrame];
        //System.out.println("sprite has refresh)
    }

    public BaseObject()
    {
        setCollisionMode(Collision.Overlap);
        CollisionArea = new Rectangle(0,0,48,48);
    }

    public void Render(Graphics2D g2)
    {
        if(!bVisible || sprite == null) return;
        g2.drawImage(sprite, screenX, screenY, SpriteRenderSizeX, SpriteRenderSizeY, null );
    }


    private final ArrayList<Entity> OtherEntity = new ArrayList<>();
    public void SimulateOverlapping(Entity otherEntity)
    {
        Tick(1f/GamePanel.FPS);
        if (getCollisionMode() == Collision.NoCollision || getCollisionMode() == Collision.Block) return;

        Rectangle entityCollision = new Rectangle();
        entityCollision.x = otherEntity.worldX + otherEntity.getCollisionArea().x;
        entityCollision.y = otherEntity.worldY + otherEntity.getCollisionArea().y;
        entityCollision.width = otherEntity.getCollisionArea().width;
        entityCollision.height = otherEntity.getCollisionArea().height;

        Rectangle worldObjectCollision = new Rectangle();

        worldObjectCollision.x = worldX + getCollisionArea().x;
        worldObjectCollision.y = worldY + getCollisionArea().y;
        worldObjectCollision.height = getCollisionArea().height;
        worldObjectCollision.width = getCollisionArea().width;

        if (entityCollision.intersects(worldObjectCollision))
        {
            if(!OtherEntity .contains(otherEntity))
            {
                OnBeginOverlapped(otherEntity);
                OtherEntity.add(otherEntity);
            }

        }
        else if(OtherEntity .contains(otherEntity))
        {
            OnEndOverlapped(otherEntity);
            OtherEntity.remove(otherEntity);
        }

    }

    public void Tick(float delta)
    {
        RunFlipBook(delta);
    }

    abstract public void OnBeginOverlapped(Entity otherEntity);

    abstract public void OnEndOverlapped(Entity otherEntity);


    @Override
    public void OnDestroy() {}

    @Override
    protected void OnAnyDamage(Entity Causer, float Damage, int SourceWorldX, int SourceWorldY) {}

    @Override
    protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY) {}
}


