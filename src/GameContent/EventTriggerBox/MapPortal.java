package GameContent.EventTriggerBox;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import GameContent.Player;
import HelpDevGameTool.ImageLoader;

import java.awt.*;

public class MapPortal extends BaseObject
{
    private int TargetMapIndex;
    private Point StartLocation = new Point();

    public MapPortal(int IndexTargetMap, int StartLocationX, int StarLocationY)
    {
        sprite = ImageLoader.LoadImage("/Objects/hint_tile.png");
        TargetMapIndex = IndexTargetMap;
        StartLocation.x = StartLocationX;
        StartLocation.y = StarLocationY;
    }

    public MapPortal(int IndexTargetMap, Point StartLocation)
    {
        sprite = ImageLoader.LoadImage("/Objects/hint_tile.png");
        TargetMapIndex = IndexTargetMap;
        this.StartLocation = StartLocation;
    }


    @Override
    public void Tick() {

    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity)
    {
        System.out.println("ChuyenMap");
        if(otherEntity instanceof Player) Telepot(TargetMapIndex,StartLocation);
    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }

    public static void Telepot(int MapIndex, int StartLocationX, int StartLocationY)
    {
        GamePanel.getInstGamePanel().currentMapIndex = MapIndex;
        GamePanel.getInstGamePanel().player.worldX = StartLocationX * GamePanel.tileSize;
        GamePanel.getInstGamePanel().player.worldY = StartLocationY * GamePanel.tileSize;
    }

    public static void Telepot(int MapIndex, Point StartLocation)
    {
        GamePanel.getInstGamePanel().currentMapIndex = MapIndex;
        GamePanel.getInstGamePanel().player.worldX = StartLocation.x * GamePanel.tileSize;
        GamePanel.getInstGamePanel().player.worldY = StartLocation.y * GamePanel.tileSize;
    }
}