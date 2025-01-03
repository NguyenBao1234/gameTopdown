package CoreGame.EffectComponent;

import CoreGame.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Lightning {

    BufferedImage darknessFilter;

    public Lightning(float radius)
    {
        // Create a buffered image
        darknessFilter = new BufferedImage(GamePanel.screenWidth, GamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        // Get the center x and y of the light circle
        int centerX = GamePanel.truePlayerScreenX + (GamePanel.tileSize)/2;
        int centerY = GamePanel.truePlayerScreenY + (GamePanel.tileSize)/2;

        //Create the 12 fraction-color segment key Gradient data :
        Color[] color = {
                new Color(0,0,0,0.1f),
                new Color(0,0,0,0.29f),
                new Color(0,0,0,0.4f),
                new Color(0,0,0,0.5f),
                new Color(0,0,0,0.59f),
                new Color(0,0,0,0.67f),
                new Color(0,0,0,0.72f),
                new Color(0,0,0,0.76f),
                new Color(0,0,0,0.79f),
                new Color(0,0,0,0.81f),
                new Color(0,0,0,0.82f),
                new Color(0,0,0,0.825f)
            };
        float fraction[] = { 0f, 0.35f, 0.5f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f,0.95f, 1f };

        // Create a gradation paint settings
        RadialGradientPaint gradient = new RadialGradientPaint(centerX, centerY, radius, fraction, color);
        g2.setPaint(gradient);

        g2.fillRect(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);

        g2.dispose();
    }
    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter,0,0,null);
    }
}
