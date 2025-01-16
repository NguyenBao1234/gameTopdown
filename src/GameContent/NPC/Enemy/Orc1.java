//Copyright POWGameStd
package GameContent.NPC.Enemy;

import POWJ.Data.Enums.Collision;
import POWJ.GamePanel;
import POWJ.SoundComponent.SoundUtility;
import HelpDevGameTool.ImageUtility;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Orc1 extends TracjectoryEnemy
{
    public Orc1(boolean horizontalTrajectory) {
        super(horizontalTrajectory);
        DeathMontage.setFpsPerImage(6);
        damageNotify.setFrameStart(2);

        AttackMontageSrc[0] = "/Character/Orc/Or1/attack/orc1_attack_full_0.png";
        AttackMontageSrc[1] = "/Character/Orc/Or1/attack/orc1_attack_full_1.png";
        AttackMontageSrc[2] = "/Character/Orc/Or1/attack/orc1_attack_full_2.png";
        AttackMontageSrc[3] = "/Character/Orc/Or1/attack/orc1_attack_full_3.png";

        OnHitMontageSrc[0] = "/Character/Orc/Or1/hurt/orc1_hurt_full_1.png";
        OnHitMontageSrc[1] = "/Character/Orc/Or1/hurt/orc1_hurt_full_0.png";
        OnHitMontageSrc[2] = "/Character/Orc/Or1/hurt/orc1_hurt_full_2.png";
        OnHitMontageSrc[3] = "/Character/Orc/Or1/hurt/orc1_hurt_full_3.png";

        Speed = 1;

        AttackBoxScaleY = 2;

        SpriteRenderSizeX = 48* GamePanel.scale;
        SpriteRenderSizeY = 48* GamePanel.scale;

        CollisionMode = Collision.Block;
        CollisionArea.x = 2* GamePanel.scale;
        CollisionArea.y = 6* GamePanel.scale;
        CollisionArea.width = 11* GamePanel.scale;
        CollisionArea.height = 10* GamePanel.scale;

        flipBookArr = new BufferedImage[4][];
        flipBookArr[0] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_0.png", 64, 64);
        flipBookArr[1] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_1.png", 64, 64);
        flipBookArr[2] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_2.png", 64, 64);
        flipBookArr[3] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_3.png", 64, 64);
    }

    @Override
    public void Tick(float delta) {
        super.Tick(delta);
    }

    @Override
    protected void HandleLocomotion() {
        super.HandleLocomotion();
        switch (GetCurrentDirection())
        {
            case up :
                SetAnimationToUse(1,7);
                break;
            case down:
                SetAnimationToUse(0,7);
                break;
            case left:
                SetAnimationToUse(2,7);
                break;
            case right:
                SetAnimationToUse(3,7);
                break;
        }
    }

    @Override
    protected void OnDeath() {
        super.OnDeath();
        switch (GetCurrentDirection())
        {
            case down :DeathMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/death/orc1_death_full_0.png",flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case up:DeathMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/death/orc1_death_full_1.png",flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case left:DeathMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/death/orc1_death_full_2.png",flipBookFrameSizeX,flipBookFrameSizeY));
                break;
            case right:DeathMontage.setFlipBook(ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/death/orc1_death_full_3.png",flipBookFrameSizeX,flipBookFrameSizeY));
                break;
        }
        PlayAnimMontage(DeathMontage);
        int ranNum = new Random().nextInt(0,6);
        if(ranNum>2)SoundUtility.playSound(1,false,"/Sound/SFX/Voice/Orc/DeathOrc_0.wav");
        else SoundUtility.playSound(1,false,"/Sound/SFX/Voice/Orc/DeathOrc_1.wav");
    }

    @Override
    protected void OnReceiveDamage() {
        super.OnReceiveDamage();
        int ranNum = new Random().nextInt(0,6);
        if(ranNum>2)SoundUtility.playSound(1,false,"/Sound/SFX/Voice/Orc/OnHitOrc_0.wav");
        else SoundUtility.playSound(1,false,"/Sound/SFX/Voice/Orc/OnHitOrc_1.wav");
    }

    @Override
    protected void Attack()
    {
        if(PlayingAnimationMontage == null && bCanAttack) SoundUtility.playSound(1,false,"/Sound/SFX/Voice/Orc/orc-grunt-3.wav");
        super.Attack();
    }
}
