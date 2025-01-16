//Copyright POWGameStd
package GameContent.EventTriggerBox;

import POWJ.EntityComponent.Entity;
import POWJ.GamePanel;
import POWJ.PlayerComponent.Player;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import HelpDevGameTool.ImageUtility;

import java.awt.*;

public class MapPortal extends ObjectPendOnPlayer
{
    private int TargetMapIndex;
    private Point StartLocation = new Point();
    private Color BgMapColor;

    public MapPortal(int IndexTargetMap, int StartLocationX, int StarLocationY, Color BgColor)
    {
        Sprite = ImageUtility.LoadImage("/Objects/hint_tile.png");
        TargetMapIndex = IndexTargetMap;
        StartLocation.x = StartLocationX;
        StartLocation.y = StarLocationY;
        BgMapColor = BgColor;
    }

    public MapPortal(int IndexTargetMap, int StartLocationX, int StarLocationY)
    {
        Sprite = ImageUtility.LoadImage("/Objects/hint_tile.png");
        TargetMapIndex = IndexTargetMap;
        StartLocation.x = StartLocationX;
        StartLocation.y = StarLocationY;
    }

    public MapPortal(int IndexTargetMap, Point StartLocation)
    {
        Sprite = ImageUtility.LoadImage("/Objects/hint_tile.png");
        TargetMapIndex = IndexTargetMap;
        this.StartLocation = StartLocation;
    }


    @Override
    public void OnBeginOverlapped(Entity otherEntity)
    {
        System.out.println("ChuyenMap");
        if(otherEntity instanceof Player)
        {
            if(BgMapColor != null )Telepot(TargetMapIndex,StartLocation.x, StartLocation.y,BgMapColor);
            else Telepot(TargetMapIndex,StartLocation);
        }
    }

    public static void Telepot(int MapIndex, int StartLocationX, int StartLocationY, Color BgColor)
    {
        GamePanel.GetInst().SetBackgroundColor(BgColor);
        GamePanel.GetInst().currentMapIndex = MapIndex;
        GamePanel.GetInst().getPlayer().worldX = StartLocationX * GamePanel.tileSize;
        GamePanel.GetInst().getPlayer().worldY = StartLocationY * GamePanel.tileSize;
    }

    public static void Telepot(int MapIndex, int StartLocationX, int StartLocationY)
    {
        GamePanel.GetInst().currentMapIndex = MapIndex;
        GamePanel.GetInst().getPlayer().worldX = StartLocationX * GamePanel.tileSize;
        GamePanel.GetInst().getPlayer().worldY = StartLocationY * GamePanel.tileSize;
    }

    public static void Telepot(int MapIndex, Point StartLocation)
    {
        GamePanel.GetInst().currentMapIndex = MapIndex;
        GamePanel.GetInst().getPlayer().worldX = StartLocation.x * GamePanel.tileSize;
        GamePanel.GetInst().getPlayer().worldY = StartLocation.y * GamePanel.tileSize;
    }
}
