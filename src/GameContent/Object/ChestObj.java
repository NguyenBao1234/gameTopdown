package GameContent.Object;

import HelpDevGameTool.ImageLoader;

public class ChestObj extends ObjectPendOnPlayer
{
    public ChestObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }
}
