package GameContent.NPC.Enemy;

import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.Data.Enums.Collision;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import HelpDevGameTool.ImageUtility;

import java.awt.image.BufferedImage;

public abstract class TracjectoryEnemy extends Enemy
{
    boolean HorizontalTrajectory = true;
    public TracjectoryEnemy(boolean horizontalTrajectory){HorizontalTrajectory = horizontalTrajectory;}

    @Override
    public void Tick(float delta)
    {
        super.Tick(delta);
    }

    @Override
    protected void HandleLocomotion() {

    }

    @Override
    protected void HandleAIMoving()
    {
        super.HandleAIMoving();
        if(PlayingAnimationMontage != null)return;

        if(HorizontalTrajectory) UpdateCurrentDirectionX(velocityDirection);
        else UpdateCurrentDirectionY(velocityDirection);

        if(vAxisX == 0 && vAxisY == 0) return;

        int collX = worldX + CollisionArea.x;
        int collY = worldY + CollisionArea.y;
        if (vAxisY > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY - Speed, CollisionArea.width, CollisionArea.height))
            {
                velocityDirection = -1;
            }
        }
        if (vAxisY < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX, collY + Speed, CollisionArea.width, CollisionArea.height))
            {
                velocityDirection = 1;
            }
        }
        if (vAxisX > 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX + Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                velocityDirection = - 1;
            }
        }
        if (vAxisX < 0)
        {
            if(!CollisionChecker.IsCollidingWithTileInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height) &
                    !CollisionChecker.IsCollidingWithObjectInBox(collX - Speed, collY, CollisionArea.width, CollisionArea.height))
            {
                velocityDirection = 1;
            }
        }
    }

    @Override
    protected void Attack()
    {
    }
    //Handle on some special event occur:
    @Override
    protected void OnReceiveDamage() {
    }
    @Override
    protected void OnDeath() {
    }
}

