package GameContent.Object;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;

public class ObjectNeedKeyItem extends BaseObject implements InteractInterface
{
    protected boolean bUnlock = false;
    public void SetUnlock(boolean unlock) {bUnlock = unlock;}
    @Override
    public boolean interact()
    {
        if(!bUnlock) return false;
        System.out.println("ObjectNeedKeyItem has interacted at unlock state");
        return true;
    }

    @Override
    public void Tick() {

    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {
        System.out.println("Overlap with"+otherEntity );
    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }


}
