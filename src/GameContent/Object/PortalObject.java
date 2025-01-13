package GameContent.Object;

import GameContent.EventTriggerBox.MapPortal;
import GameContent.EventTriggerBox.TextTrigger;

public class PortalObject extends TextTrigger
{
    private final int mapIndex;
    private final int StartLocX,StartLocY;
    public PortalObject(int mapToTrans, int StartX, int StartY)
    {
        super("Interact to Enter");
        mapIndex = mapToTrans;
        StartLocX = StartX;
        StartLocY = StartY;
    }

    @Override
    public void interact() {
        super.interact();
        MapPortal.Telepot(mapIndex, StartLocX, StartLocY);
    }
}
