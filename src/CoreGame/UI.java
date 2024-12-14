package CoreGame;

import CoreGame.Data.Enums.GameState;

import java.awt.*;
import java.awt.Color;
import  java.awt.Graphics2D;

public class UI
{
    Font arial_40, arial_80B;
    Graphics2D g2;
    public int commandNum = 0;
    public UI (GamePanel gamePanel)
    {
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.PLAIN, 80);
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        switch(GamePanel.getInstGamePanel().gameState)
        {
            case GameState.Tittle :
                System.out.println("Title");
                drawTileScreen();
                break;
            case GameState.Run:
                break;
            case GameState.Pause:
                drawPauseScreen();
                break;
        }
    }

    public void drawTileScreen ()
    {
        System.out.println("Actual Tittle drawing");
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64));
        String text = "TopDown2DGame";
        int x = 64* GamePanel.scale;
        int y = 64* GamePanel.scale;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));
        text = "New game";
        x = 64* GamePanel.scale;
        y += GamePanel.tileSize *2;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">",x- GamePanel.tileSize, y );
        }

        text = "Load game";
        x = 64* GamePanel.scale;
        y += GamePanel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x- GamePanel.tileSize, y );
        }
        text = "Quit";
        x = 64* GamePanel.scale;
        y += GamePanel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">",x- GamePanel.tileSize, y );
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSE";
        int x = 64* GamePanel.scale;
        int y = 64* GamePanel.scale;
        g2.drawString (text, x , y);
    }

}
