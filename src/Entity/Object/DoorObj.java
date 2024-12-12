package Entity.Object;

import CoreGame.Enums.Collision;
import CoreGame.EntityComponent.ObjectNeedKeyItem;
import HelpDevGameTool.ImageLoader;

public class DoorObj extends ObjectNeedKeyItem
{
    public DoorObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/closed_door.png");
        collisionMode = Collision.Block;
    }

    @Override
    public boolean interact() {
        super.interact();
        if(!bUnlock) return false;
        sprite = ImageLoader.LoadImage("/Objects/opened_door.png");
        setCollisionMode(Collision.NoCollision);
        return true;
    }
}
