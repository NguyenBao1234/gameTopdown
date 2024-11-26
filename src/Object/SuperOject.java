package Object;

import CoreGame.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class SuperOject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea  = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw (Graphics2D g2)
    {
        int screenX = worldX - GamePanel.getInstGamePanel().player.worldX + GamePanel.truePlayerScreenX;
        int screenY = worldY - GamePanel.getInstGamePanel().player.worldY + GamePanel.truePlayerScreenY;


            g2.drawImage(image, screenX, screenY, 16*GamePanel.scale, 16*GamePanel.scale, null );

        }
    }


