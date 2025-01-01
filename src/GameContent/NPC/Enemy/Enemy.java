package GameContent.NPC.Enemy;

import CoreGame.EntityComponent.BaseCharacter;
import CoreGame.EntityComponent.Entity;

public class Enemy extends BaseCharacter
{
    private float Damage = 5;

    private float health = 40;

    public float getDamage() {return Damage;}

    public void setDamage(float damage) {Damage = damage;}

    public float getHealth() {return health;}

    public void setHealth(float health) {
        this.health = health;}

    @Override
    protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY)
    {
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
}
