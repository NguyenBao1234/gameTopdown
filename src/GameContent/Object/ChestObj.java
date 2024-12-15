package GameContent.Object;

import CoreGame.EntityComponent.Entity;
import CoreGame.EntityComponent.BaseObject;
import HelpDevGameTool.ImageLoader;

public class ChestObj extends BaseObject
{
    public ChestObj()
    {
        sprite = ImageLoader.LoadImage("/Objects/chest.png");
    }

    @Override
    public void Tick() {
    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

}
