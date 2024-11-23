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
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];
    int normalizedPlayerScreenX;
    int normalizedPlayerScreenY;
    public TileManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        normalizedPlayerScreenX = gamePanel.screenWidth / 2 - gamePanel.tileSize/2;
        normalizedPlayerScreenY = gamePanel.screenHeight / 2 - gamePanel.tileSize/2;
        tile = new Tile[20]; //if ya want more kinda tile, ya can change 20 to the number ya want
        mapTileNum = new int[50][50];
        getTileImage();

        loadMap("/Map/map3.txt");
    }

    public void getTileImage()
    {
        try
        {
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/sand.png")));

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/water.png")));

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
                    mapTileNum[col][row] = num;
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

        while (worldcol < 50 && worldrow < 50) {
            int tileNum = mapTileNum[worldcol][worldrow];
            int worldX = worldcol * gamePanel.tileSize;
            int worldY = worldrow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + normalizedPlayerScreenX;
            int screenY = worldY - gamePanel.player.worldY + normalizedPlayerScreenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - normalizedPlayerScreenX
                    && worldX < gamePanel.player.worldX + normalizedPlayerScreenX + 48
                    && worldY + gamePanel.tileSize > gamePanel.player.worldY - normalizedPlayerScreenY
                    && worldY < gamePanel.player.worldY + normalizedPlayerScreenY + 48){
                //g2.drawImage(tile[tileNum].image, screenX, screenY, 64*gp.scale, 64*gp.scale, null);
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldcol++;
            if (worldcol == 50){
                worldcol=0;
                worldrow++;
            }
        }
    }
}
