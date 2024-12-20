package GameContent.NPC.Enemy;

import CoreGame.EntityComponent.BaseCharacter;

public class Enemy extends BaseCharacter
{
    private float Damage = 5;

    private float Health = 40;

    public float getDamage() {return Damage;}

    public void setDamage(float damage) {Damage = damage;}

    public float getHealth() {return Health;}

    public void setHealth(float health) {Health = health;}

}
