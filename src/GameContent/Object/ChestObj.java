package GameContent.Object;

import HelpDevGameTool.ImageLoader;

public class ChestObj extends BaseObjectPendOnPlayer
{
    public ChestObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }
}
