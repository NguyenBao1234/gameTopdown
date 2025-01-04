package GameContent.Object.MasterObject;

import CoreGame.EntityComponent.Entity;
import CoreGame.Data.Enums.Collision;
import CoreGame.SoundComponent.SoundUtility;
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
        SoundUtility.playSound(1,false,"/Sound/SFX/coin.wav");
        Destroy(this);
    }
}
