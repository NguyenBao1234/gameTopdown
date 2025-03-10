package GameContent.Object.MasterObject;

import POWJ.EntityComponent.Entity;
import POWJ.Data.Enums.Collision;
import POWJ.SoundComponent.SoundUtility;
import HelpDevGameTool.ImageUtility;


public class KeyItemObject extends InteractObject
{
    protected ObjectNeedKeyItem targetInteractObject;

    public KeyItemObject(ObjectNeedKeyItem ObjectNeedInteract)
    {
        CollisionMode = Collision.Overlap;
        Sprite = ImageUtility.LoadImage("/Objects/key.png");
        targetInteractObject = ObjectNeedInteract;
    }

    public KeyItemObject(Entity ObjectNeedInteract)
    {
        CollisionMode = Collision.Overlap;
        Sprite = ImageUtility.LoadImage("/Objects/key.png");
        targetInteractObject = (ObjectNeedKeyItem) ObjectNeedInteract;
    }

    @Override
    public void interact()
    {
        System.out.println("Key was interacted");
        if(targetInteractObject != null) targetInteractObject.SetUnlock(true);
        SoundUtility.playSound(1,false, "/Sound/SFX/Object/pick-up-item-1.wav");
        Destroy(this);
    }
}
