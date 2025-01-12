package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.SoundComponent.SoundUtility;
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
        SoundUtility.playSound(1,false,"/Sound/SFX/Object/wood.wav");
        if(!bUnlock) return ;
        super.interact();
        SoundUtility.playSound(1,false,"/Sound/SFX/Object/DoorOpen.wav");
        setCollisionMode(Collision.NoCollision);
    }
}
