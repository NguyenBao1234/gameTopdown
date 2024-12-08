package Tile;

import CoreGame.GamePanel;
import HelpDevGameTool.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager
{
    public Tile[] tiles;
    public int tileTypeMap[][][]; // maps/cols/rows
    int normalizedPlayerScreenX;
    int normalizedPlayerScreenY;
    public TileManager()
    {
        normalizedPlayerScreenX = GamePanel.screenWidth / 2 - GamePanel.tileSize/2;
        normalizedPlayerScreenY = GamePanel.screenHeight / 2 - GamePanel.tileSize/2;
        tiles = new Tile[20]; //if ya want more kinda tile, ya can change 20 to the number ya want
        tileTypeMap = new int[GamePanel.maxMap][50][50];
        getTileImage();

        loadMap("/Map/map3.txt",0);
        loadMap("/Map/map4.txt",1);
    }

    public void getTileImage()
    {
            tiles[0] = new Tile();
            tiles[0].image = ImageLoader.LoadImage("/Tile/water.png");
            tiles[0].collision = true;

            tiles[1] = new Tile();
            tiles[1].image = ImageLoader.LoadImage("/Tile/sand.png");
            tiles[1].collision = false;

            tiles[2] = new Tile();
            tiles[2].image = ImageLoader.LoadImage("/Tile/wall.png");
            tiles[2].collision = false;
    }

    public void loadMap(String filePath,int map)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0;
            int row = 0;
            while (col < 50 && row < 50)
            {
                String line = bufferedReader.readLine();
                while (col < 50)
                {
                    String numbers[] = line.split("");
                    int num = Integer.parseInt(numbers[col]);
                    tileTypeMap[map][col][row] = num;
                    col++;
                }
                if(col== 50){
                    col=0;
                    row++;
                }
            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldcol = 0;
        int worldrow = 0;
        int playerWorldX = GamePanel.getInstGamePanel().player.worldX;
        int playerWorldY = GamePanel.getInstGamePanel().player.worldY;

        while (worldcol < 50 && worldrow < 50) {
            int tileNum = tileTypeMap[GamePanel.getInstGamePanel().currentMap][worldcol][worldrow];
            int worldX = worldcol * GamePanel.tileSize;
            int worldY = worldrow * GamePanel.tileSize;
            int screenX = worldX - playerWorldX + normalizedPlayerScreenX;
            int screenY = worldY - playerWorldY + normalizedPlayerScreenY;

            if (worldX + GamePanel.tileSize > playerWorldX - normalizedPlayerScreenX
                    && worldX < playerWorldX + normalizedPlayerScreenX + 48
                    && worldY + GamePanel.tileSize > playerWorldY - normalizedPlayerScreenY
                    && worldY < playerWorldY + normalizedPlayerScreenY + 48){
                //g2.drawImage(tile[tileNum].image, screenX, screenY, 64*gp.scale, 64*gp.scale, null);
                g2.drawImage(tiles[tileNum].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            }

            worldcol++;
            if (worldcol == 50){
                worldcol=0;
                worldrow++;
            }
        }
    }
}
