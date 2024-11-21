package Tile;

import CoreGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20]; //if ya want more kinda tile, ya can change 20 to the number ya want
        mapTileNum = new int[50][50];
        getTileImage();
        loadMap("/Map/map3.txt");
    }

    public void getTileImage() {
        try {

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/sand.png")));

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/water.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < 50 && row < 50) {
                String line = br.readLine();
                while (col < 50) {
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
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < 50 && worldrow < 50) {
            int tileNum = mapTileNum[worldcol][worldrow];
            int worldX = worldcol * 64*gp.scale;
            int worldY = worldrow * 64*gp.scale;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + 64*gp.scale > gp.player.worldX - gp.player.screenX
                    && worldX + 64*gp.scale < gp.player.worldX + gp.player.screenX
                    && worldY + 64*gp.scale > gp.player.worldY - gp.player.screenY
                    && worldY - 64*gp.scale < gp.player.worldY + gp.player.screenY) {
                //g2.drawImage(tile[tileNum].image, screenX, screenY, 64*gp.scale, 64*gp.scale, null);
            }
            g2.drawImage(tile[tileNum].image, screenX, screenY, 64*gp.scale, 64*gp.scale, null);
            worldcol++;
            if (worldcol == 50){
                worldcol=0;
                worldrow++;

            }
        }

    }

}
