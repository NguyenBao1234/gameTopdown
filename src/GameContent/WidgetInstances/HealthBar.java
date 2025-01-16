package GameContent.WidgetInstances;

import POWJ.WidgetComponent.Widget;
import java.awt.*;

public class HealthBar extends Widget {
    private float maxhealth;
    private float currenthealth;


    public HealthBar(float maxHealth, float currentHealth) {
        this.maxhealth = maxHealth;
        this.currenthealth = currentHealth;
    }


    public void updateHealth(float currenthealth) {
        this.currenthealth = currenthealth;
    }



    @Override
    public void Draw(Graphics2D g2)
    {
        int barX = 20;
        int barY = 20;
        int barWidth = 200;
        int barHeight = 20;
        float healthPercentage = currenthealth / maxhealth;

        // Vẽ khung thanh máu
        g2.setColor(Color.BLACK);
        g2.drawRect(barX, barY, barWidth, barHeight);

        // Hiển thị số lượng máu
        if (healthPercentage > 0.5) {
            g2.setColor(Color.GREEN);
        } else if (healthPercentage > 0.25) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.RED);
        }
        g2.fillRect(barX, barY, (int) (healthPercentage * barWidth), barHeight);
        g2.setColor(Color.WHITE);
        g2.drawString("HP: " + (int) currenthealth + " / " + (int) maxhealth, barX + 50, barY + 15);
    }
}
