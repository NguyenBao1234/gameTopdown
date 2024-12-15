package CoreGame.EntityComponent;

import CoreGame.Data.Enums.Collision;
import CoreGame.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public abstract class BaseObject extends Entity
{
    protected boolean bVisible = true;
    public BaseObject()
    {
        setCollisionMode(Collision.Overlap);
        collisionArea = new Rectangle(0,0,48,48);
    }

    public void draw (Graphics2D g2)
    {
        if(!bVisible || sprite == null) return;
        int screenX = worldX - GamePanel.GetInst().player.worldX + GamePanel.truePlayerScreenX;
        int screenY = worldY - GamePanel.GetInst().player.worldY + GamePanel.truePlayerScreenY;
        g2.drawImage(sprite, screenX, screenY, 16*GamePanel.scale, 16*GamePanel.scale, null );
    }


    private final ArrayList<Entity> OtherEntity = new ArrayList<>();
    public void SimulateOverlapping(Entity otherEntity)
    {
        Tick();
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

    abstract public void Tick();

    abstract public void OnBeginOverlapped(Entity otherEntity);

    abstract public void OnEndOverlapped(Entity otherEntity);

}


