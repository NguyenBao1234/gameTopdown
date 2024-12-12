package CoreGame.EntityComponent;

import CoreGame.Enums.Collision;
import CoreGame.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public abstract class BaseObject extends Entity
{
    protected boolean bVisible = true;
    public BaseObject()
    {
        collisionArea = new Rectangle(0,0,48,48);
    }

    public void draw (Graphics2D g2)
    {
        if(!bVisible || sprite == null) return;
        int screenX = worldX - GamePanel.getInstGamePanel().player.worldX + GamePanel.truePlayerScreenX;
        int screenY = worldY - GamePanel.getInstGamePanel().player.worldY + GamePanel.truePlayerScreenY;
        g2.drawImage(sprite, screenX, screenY, 16*GamePanel.scale, 16*GamePanel.scale, null );
    }


    private ArrayList<Entity> OtherEntity = new ArrayList<>();
    public void SimulateOverlapping(Entity otherEntity)
    {
        Tick();
        if (getCollisionMode() == Collision.NoCollision || getCollisionMode() == Collision.Block) return;

        Rectangle entityCollision = new Rectangle();
        entityCollision.x = otherEntity.worldX + otherEntity.getCollisionArea().x;
        entityCollision.y = otherEntity.worldY + otherEntity.getCollisionArea().y;
        entityCollision.width = otherEntity.getCollisionArea().width;
        entityCollision.height = otherEntity.getCollisionArea().height;

        Rectangle worldObjecCollision = new Rectangle();

        worldObjecCollision.x = worldX + getCollisionArea().x;
        worldObjecCollision.y = worldY + getCollisionArea().y;
        worldObjecCollision.height = getCollisionArea().height;
        worldObjecCollision.width = getCollisionArea().width;

        if (entityCollision.intersects(worldObjecCollision))
        {
            if(!OtherEntity .contains(otherEntity)) OnBeginOverlapped(otherEntity);
            OtherEntity.add(otherEntity);
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


