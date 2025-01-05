package GameContent.NPC.Enemy;

import CoreGame.AnimationClass.AnimMontage;
import CoreGame.EntityComponent.Entity;
import GameContent.NPC.BaseCharacterPendOnPlayer;
import GameContent.NotifyInstances.TraceDamageNotify;

public abstract class Enemy extends BaseCharacterPendOnPlayer
{
    protected float Damage = 5;
    protected float health = 40;

    protected TraceDamageNotify damageNotify= new TraceDamageNotify(0, this, 2, 2);
    protected String  AnimPaths[];
    protected AnimMontage AttackMontage = new AnimMontage();

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

    public float getDamage() {return Damage;}

    public void setDamage(float damage) {Damage = damage;}

    public float getHealth() {return health;}

    public void setHealth(float health) {
        this.health = health;}

}
