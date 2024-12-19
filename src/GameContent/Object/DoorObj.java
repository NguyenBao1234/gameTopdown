package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import HelpDevGameTool.ImageLoader;

public class DoorObj extends ObjectNeedKeyItem
{
    public DoorObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/closed_door.png");
        CollisionMode = Collision.Block;
    }

    @Override
    public void interact() {
        super.interact();
        if(!bUnlock) return ;
        sprite = ImageLoader.LoadImage("/Objects/opened_door.png");
        setCollisionMode(Collision.NoCollision);
    }
}
