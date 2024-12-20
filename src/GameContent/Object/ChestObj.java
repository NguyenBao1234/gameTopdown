package GameContent.Object;

import HelpDevGameTool.ImageLoader;

public class ChestObj extends ObjectPendOnPlayer
{
    public ChestObj()
    {
        Sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }
}
