//Copyright POWGameStd
package POWJ.EntityComponent;

import POWJ.Data.Enums.Collision;
import POWJ.GamePanel;

import java.awt.*;

public abstract class Entity
{
    public int worldX,worldY;

    protected Collision CollisionMode = Collision.NoCollision;
    protected Rectangle CollisionArea;

    public final Rectangle getCollisionArea(){ return CollisionArea;}
    public void setCollisionArea(int x, int y, int width, int height)
    {
        CollisionArea.x = x;
        CollisionArea.y = y;
        CollisionArea.width = width;
        CollisionArea.height = height;
    }
    public final Collision getCollisionMode(){return CollisionMode;}
    public final void setCollisionMode(Collision collision){
        CollisionMode = collision;}

    public final void Destroy(Entity entity)
    {
        for (int i = 0; i < GamePanel.GetInst().obj.length; i++)
        {
            for (int j = 0; j < GamePanel.GetInst().obj[i].length; j++)
            {
                if (GamePanel.GetInst().obj[i][j] != entity) continue;
                GamePanel.GetInst().obj[i][j] = null;
                entity.OnDestroy();
            }
        }
    }

    /**
     * When Destroy Entity, This Function is called
     */
    abstract public void OnDestroy();
//------------------------------------------------------------------------
    /**
     * Make simple damage
     * @param Receiver :Entity receive Damage
     * @param Causer: Entity make Damage
     * @param Damage: Amount of Damage
     */
    public void ApplyDamage(Entity Receiver, Entity Causer, float Damage)
    {
        if(Damage == 0 ) return;
        Receiver.OnAnyDamage(Causer, Damage,0,0);
    }
//------------------------------------------------------------------------
    /**
     * Make damage with location of damage's source
     * @param Receiver Entity receive Damage
     * @param Causer Entity make Damage
     * @param Damage Amount of Damage
     * @param SourceWorldX location X of Damage's Source
     * @param SourceWorldY location Y of Damage's Source
     */
    public void ApplyDamage(Entity Receiver, Entity Causer, float Damage, int SourceWorldX, int SourceWorldY)
    {
        if(Damage == 0 ) return;
        Receiver.OnAnyDamage(Causer, Damage, SourceWorldX, SourceWorldY);
    }
//------------------------------------------------------------------------
    /**
     * Make damage with location of damage
     * @param Receiver :Entity receive Damage
     * @param Causer Entity make Damage
     * @param Damage Amount of Damage
     * @param WorldX Damage at location X
     * @param WorldY Damage at location Y
     * @param SourceWorldX location X of Damage's Source
     * @param SourceWorldY location Y of Damage's Source
     */
    public void ApplyPointDamage(Entity Receiver, Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY)
    {
        if(Damage == 0 ) return;
        Receiver.OnPointDamage(Causer, Damage, WorldX, WorldY, SourceWorldX, SourceWorldY);
        Receiver.OnAnyDamage(Causer, Damage, SourceWorldX, SourceWorldY);
    }
//------------------------------------------------------------------------
    /**
     * When Apply any Damage to this Entity, this Function is called
     * @param Causer Entity make Damage
     * @param Damage Amount of Damage
     * @param SourceWorldX location X of Damage's Source
     * @param SourceWorldY  location Y of Damage's Source
     */
    abstract protected void OnAnyDamage(Entity Causer, float Damage, int SourceWorldX, int SourceWorldY);
//------------------------------------------------------------------------
    /**
     * When Apply Point damage to this Entity, this Function is called
     * @param Causer Entity make Damage
     * @param Damage Amount of Damage
     * @param WorldX Damage at location X
     * @param WorldY Damage at location Y
     * @param SourceWorldX location X of Damage's Source
     * @param SourceWorldY  location Y of Damage's Source
     */
    abstract protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY);
}
