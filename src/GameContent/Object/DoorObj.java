package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import GameContent.Object.MasterObject.ObjectNeedKeyItem;

public class DoorObj extends ObjectNeedKeyItem
{
    public DoorObj()
    {
        super("/Objects/Door/closed_door.png","/Objects/Door/opened_door.png");
        CollisionMode = Collision.Block;
    }

    @Override
    public void interact()
    {
        if(!bUnlock) return ;
        super.interact();
        setCollisionMode(Collision.NoCollision);
    }
}
