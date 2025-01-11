package GameContent.NPC.Enemy;

import HelpDevGameTool.ImageUtility;

import java.awt.image.BufferedImage;

public class Orc1 extends TracjectoryEnemy
{

    public Orc1(boolean horizontalTrajectory) {
        super(horizontalTrajectory);

        AttackMontageSrc[0] = "/Character/Orc/Or1/attack/orc1_attack_full_0.png";
        AttackMontageSrc[1] = "/Character/Orc/Or1/attack/orc1_attack_full_1.png";
        AttackMontageSrc[2] = "/Character/Orc/Or1/attack/orc1_attack_full_2.png";
        AttackMontageSrc[3] = "/Character/Orc/Or1/attack/orc1_attack_full_3.png";

        OnHitMontageSrc[0] = "/Character/Orc/Or1/hurt/orc1_hurt_full_0.png";
        OnHitMontageSrc[1] = "/Character/Orc/Or1/hurt/orc1_hurt_full_1.png";
        OnHitMontageSrc[2] = "/Character/Orc/Or1/hurt/orc1_hurt_full_2.png";
        OnHitMontageSrc[3] = "/Character/Orc/Or1/hurt/orc1_hurt_full_3.png";

        SpriteRenderSizeX = 64;
        SpriteRenderSizeY = 64;

        flipBookArr = new BufferedImage[4][];
        flipBookArr[0] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_0.png", SpriteRenderSizeX, SpriteRenderSizeY);
        flipBookArr[1] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_1.png", SpriteRenderSizeX, SpriteRenderSizeY);
        flipBookArr[2] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_2.png", SpriteRenderSizeX, SpriteRenderSizeY);
        flipBookArr[3] = ImageUtility.MakeFlipBookFromSheet("/Character/Orc/Or1/walk/orc1_walk_full_3.png", SpriteRenderSizeX, SpriteRenderSizeY);
    }

    @Override
    protected void HandleLocomotion() {
        super.HandleLocomotion();
        switch (GetCurrentDirection())
        {
            case up :
                SetAnimationToUse(1,4);
                break;
            case down:
                SetAnimationToUse(0,4);
                break;
            case left:
                SetAnimationToUse(2,4);
                break;
            case right:
                SetAnimationToUse(3,4);
                break;
        }
    }
}
