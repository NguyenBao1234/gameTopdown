package CoreGame.MapComponent;

import CoreGame.GamePanel;
import HelpDevGameTool.ImageLoader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import static CoreGame.Data.Enums.Collision.Block;
import static CoreGame.Data.Enums.Collision.NoCollision;

public class TileManager
{
    private static TileManager Inst;
    public static final Tile[] tiles = new Tile[10]; // Types of tile
    private static final ArrayList<int[][]> tileTypeMapList = new ArrayList<>(GamePanel.maxMap);
    public static int tileTypeMap[][][] ; // [map index][col][row]
    private static final int[] maxWidth = new int[GamePanel.maxMap];

    private static final int normalizedPlayerScreenX= GamePanel.screenWidth / 2 - GamePanel.tileSize/2;
    private static final int normalizedPlayerScreenY= GamePanel.screenHeight / 2 - GamePanel.tileSize/2;

    public TileManager()
    {
        Inst = this;
        getTileImage();
        LoadMap("/Map/map3.txt",0);
        LoadMap("/Map/map4.txt",1);
        tileTypeMap = tileTypeMapList.toArray(new int[0][][]);
    }

    public static TileManager GetInst()
    {
        if (Inst != null) Inst = new TileManager();
        return Inst;
    }

    public void getTileImage()
    {
            tiles[0] = new Tile();
            tiles[0].image = ImageLoader.LoadImage("/Tile/water.png");
            tiles[0].collision = Block;

            tiles[1] = new Tile();
            tiles[1].image = ImageLoader.LoadImage("/Tile/sand.png");
            tiles[1].collision = NoCollision;

            tiles[2] = new Tile();
            tiles[2].image = ImageLoader.LoadImage("/Tile/wall.png");
            tiles[2].collision = NoCollision;
    }

    public void LoadMap(final String filePath,final int mapIndex)
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
            int col = 0;
            int row ;
            int maxRow = 0;

            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine())!=null)
            {
                maxRow++;
                lines.add(line);
                maxWidth[mapIndex] = Math.max(maxWidth[mapIndex], line.split(" +").length);
            }
            int [][] TempMap = new int[maxRow][];

            for( row = 0 ; row< maxRow ; row++)
            {
                String typeStringIndexes[] = lines.get(row).split(" +");
                int typeIndexes[] = new int[typeStringIndexes.length];
                while (col < typeIndexes.length) {
                    String numberString = typeStringIndexes[col];
                    int typeIndex;
                    if (numberString.isBlank() || !numberString.matches("-?\\d+")) typeIndex = -1;
                    else typeIndex = Integer.parseInt(numberString);
                    typeIndexes[col] = typeIndex;
                    col++;
                }
                col = 0;
                TempMap[row] = typeIndexes;
                bufferedReader.close();
            }
            tileTypeMapList.add(TempMap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void DrawTiles(final Graphics2D g2)
    {
        int currMapIndex = GamePanel.GetInst().currentMapIndex;
        int playerWorldX = GamePanel.GetInst().player.worldX;
        int playerWorldY = GamePanel.GetInst().player.worldY;
        if(tileTypeMap[currMapIndex] == null) return;
        for(int i = 0; i < tileTypeMap[currMapIndex].length; i++)
        {
            if(tileTypeMap[currMapIndex][i] == null) return;
            for(int j = 0; j < tileTypeMap[currMapIndex][i].length; j++)
            {
                int typeTileIndex = tileTypeMap[currMapIndex][i][j];
                if(typeTileIndex<0) continue;
                int worldX = j * GamePanel.tileSize;
                int worldY = i * GamePanel.tileSize;
                int screenX = worldX - playerWorldX + normalizedPlayerScreenX;
                int screenY = worldY - playerWorldY + normalizedPlayerScreenY;
                if
                (
                        worldX + GamePanel.tileSize > playerWorldX - normalizedPlayerScreenX
                        && worldX < playerWorldX + normalizedPlayerScreenX + GamePanel.tileSize
                        && worldY + GamePanel.tileSize > playerWorldY - normalizedPlayerScreenY
                        && worldY < playerWorldY + normalizedPlayerScreenY + GamePanel.tileSize
                )
                {
                    g2.drawImage(tiles[typeTileIndex].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
                }
            }
        }
    }

    public static int GetWidthOfCurrentMap() {return maxWidth[GamePanel.GetInst().currentMapIndex];}

    public static int GetHeightOfCurrentMap(){return tileTypeMap[GamePanel.GetInst().currentMapIndex].length;}

    public static int GetWidthOfMap( int currentMap)
    {
        if (currentMap >= tileTypeMap.length) return 0;
        return tileTypeMap[currentMap][1].length;
    }

    public static int GetHeightOfMap( int currentMap)
    {
        if (currentMap >= tileTypeMap.length) return 0;
        return tileTypeMap[currentMap].length;
    }
}
