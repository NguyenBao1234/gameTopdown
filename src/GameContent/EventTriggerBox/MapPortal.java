package GameContent.EventTriggerBox;

import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import CoreGame.PlayerComponent.Player;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import HelpDevGameTool.ImageUtility;

import java.awt.*;

public class MapPortal extends ObjectPendOnPlayer
{
    private int TargetMapIndex;
    private Point StartLocation = new Point();

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
        if(otherEntity instanceof Player) Telepot(TargetMapIndex,StartLocation);
    }

    public static void Telepot(int MapIndex, int StartLocationX, int StartLocationY)
    {
        GamePanel.GetInst().currentMapIndex = MapIndex;
        GamePanel.GetInst().player.worldX = StartLocationX * GamePanel.tileSize;
        GamePanel.GetInst().player.worldY = StartLocationY * GamePanel.tileSize;
    }

    public static void Telepot(int MapIndex, Point StartLocation)
    {
        GamePanel.GetInst().currentMapIndex = MapIndex;
        GamePanel.GetInst().player.worldX = StartLocation.x * GamePanel.tileSize;
        GamePanel.GetInst().player.worldY = StartLocation.y * GamePanel.tileSize;
    }
}
