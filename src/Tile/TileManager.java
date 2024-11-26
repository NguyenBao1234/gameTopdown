package Tile;

import CoreGame.GamePanel;

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
    public int tileTypeMap[][];
    int normalizedPlayerScreenX;
    int normalizedPlayerScreenY;
    public TileManager()
    {
        normalizedPlayerScreenX = GamePanel.screenWidth / 2 - GamePanel.tileSize/2;
        normalizedPlayerScreenY = GamePanel.screenHeight / 2 - GamePanel.tileSize/2;
        tiles = new Tile[20]; //if ya want more kinda tile, ya can change 20 to the number ya want
        tileTypeMap = new int[50][50];
        getTileImage();

        loadMap("/Map/map3.txt");
    }

    public void getTileImage()
    {
        try
        {
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/sand.png")));
            tiles[1].collision = false;

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/water.png")));
            tiles[0].collision = true;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < 50 && row < 50)
            {
                String line = bufferedReader.readLine();
                while (col < 50)
                {
                    String numbers[] = line.split("");
                    int num = Integer.parseInt(numbers[col]);
                    tileTypeMap[col][row] = num;
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
            int tileNum = tileTypeMap[worldcol][worldrow];
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
