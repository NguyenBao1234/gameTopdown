package CoreGame.EntityComponent;

import CoreGame.Data.Enums.Collision;
import java.awt.*;

public abstract class Entity
{
    public int worldX,worldY;

    protected boolean bColliding = false;

    protected Collision CollisionMode = Collision.NoCollision;
    protected Rectangle CollisionArea;



    public final Rectangle getCollisionArea(){ return CollisionArea;}
    public final Collision getCollisionMode(){return CollisionMode;}
    public final void setCollisionMode(Collision collision){
        CollisionMode = collision;}
    public final boolean IsColliding(){return bColliding;}
    public final void SetColliding(boolean bCollide){bColliding = bCollide; }
    public final void Destroy(Entity entity)
    {
        entity.OnDestroy();
        entity = null;
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
        OnPointDamage(Causer, Damage, WorldX, WorldY, SourceWorldX, SourceWorldY);
    }
//------------------------------------------------------------------------
    /**
     * When Apply Damage to this Entity, this Function is called
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
