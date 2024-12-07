package Entity.Object.Instance;

import Entity.Object.Master.BaseObject;
import HelpDevGameTool.ImageLoader;

public class ChestObj extends BaseObject
{
    public ChestObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }
}
