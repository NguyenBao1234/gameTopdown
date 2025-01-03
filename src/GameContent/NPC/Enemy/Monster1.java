package GameContent.NPC.Enemy;

import CoreGame.AnimationClass.AnimMontage;
import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import GameContent.NotifyInstances.TraceDamageNotify;
import HelpDevGameTool.ImageLoader;

import java.awt.image.BufferedImage;

public class Monster1 extends Enemy {
    private TraceDamageNotify damageNotify= new TraceDamageNotify(0, this, 2, 2);;
    private AnimMontage AttackMontage= new AnimMontage();;
    public Monster1()
    {
        flipBookArr = new BufferedImage[4][];
        // Set up basic monster properties
        setDamage(8); // Example: Adjust the damage value as needed
        setHealth(50); // Example: Adjust the health value as needed

        SetupAnimations();
        SpriteRenderSizeX = 16 * GamePanel.scale;
        SpriteRenderSizeY = 16 * GamePanel.scale;
        AttackMontage.AddNotify(damageNotify);
        }

    @Override
    public void Tick(float delta)
    {
        super.Tick(delta);
        for (BaseObject object : CollisionChecker.GetOverlappedObjectsInBox(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize)) {
            if (object instanceof MainPlayer) {
                MainPlayer player = (MainPlayer) object;
                player.setHealth(player.getHealth() - getDamage());
                attackPlayer();
                System.out.println("Attacked player");
                break; // Attack the first player found
            }
        }
    }

    void SetupAnimations()
    {
        flipBookArr[0] = ImageLoader.makeFlipBook("/Mushroom/idle");
    }

    public void attack()
    {
        if (animMontage != null) {
            return; // Prevent overlapping animations
        }

        damageNotify.setFrameStart(0);
        AttackMontage.setFlipBook(ImageLoader.makeFlipBook("/Mushroom/attack"));

        PlayAnimMontage(AttackMontage, 4); // Play animation with a frame duration of 4
    }

        public void attackPlayer() {
            if (animMontage == null) {
                attack(); // Use the attack method to initiate the attack animation
            }
        }

        @Override
        protected void OnPointDamage(Entity Causer, float Damage, int WorldX, int WorldY, int SourceWorldX, int SourceWorldY) {
            super.OnPointDamage(Causer, Damage, WorldX, WorldY, SourceWorldX, SourceWorldY);
            if (getHealth() <= 0) {
                DeathAnim();
            } else {
                ReceiveDamageAnim();
            }
        }

        private void ReceiveDamageAnim() {
            // Implement damage animation logic here

        }

        private void DeathAnim() {
            // Implement death animation logic here

        }
    }

