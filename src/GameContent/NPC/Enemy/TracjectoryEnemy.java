//Copyright POWGameStd
package GameContent.NPC.Enemy;

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
    protected boolean HandleAIMoving()
    {
        if(PlayingAnimationMontage != null)return false;
        if(bCanAttack) return false;
        if(HorizontalTrajectory) UpdateCurrentDirectionX(velocityDirection);
        else UpdateCurrentDirectionY(velocityDirection);

        if(!super.HandleAIMoving()) velocityDirection *=-1;
        return true;
    }

    @Override
    protected void OnDeath() {
    }
}

