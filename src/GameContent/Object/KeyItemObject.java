package GameContent.Object;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.Data.Enums.Collision;
import HelpDevGameTool.ImageLoader;


public class KeyItemObject extends BaseObject implements InteractInterface
{
    protected ObjectNeedKeyItem targetInteractObject;

    public KeyItemObject(ObjectNeedKeyItem ObjectNeedInteract)
    {
        collisionMode = Collision.Overlap;
        sprite = ImageLoader.LoadImage("/Objects/key.png");
        targetInteractObject = ObjectNeedInteract;
    }

    public KeyItemObject(BaseObject ObjectNeedInteract)
    {
        collisionMode = Collision.Overlap;
        sprite = ImageLoader.LoadImage("/Objects/key.png");
        targetInteractObject = (ObjectNeedKeyItem) ObjectNeedInteract;
    }

    @Override
    public boolean interact()
    {
        System.out.println("Key was interacted");
        sprite = null;
        setCollisionMode(Collision.NoCollision);
        targetInteractObject.SetUnlock(true);
        return true;
    }

    @Override
    public void Tick() {}

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {}

    @Override
    public void OnEndOverlapped(Entity otherEntity) {}
}