package GameContent.Object;

import CoreGame.EntityComponent.Entity;
import CoreGame.EntityComponent.BaseObject;
import HelpDevGameTool.ImageLoader;

public class ChestObj extends ObjectPendingOnPlayer
{
    public ChestObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }
}
