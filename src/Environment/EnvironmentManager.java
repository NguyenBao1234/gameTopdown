package Environment;

import CoreGame.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel GamePanel;
    Lightning lightning;

    public EnvironmentManager(GamePanel GamePanel){
        this.GamePanel = GamePanel;
    }

    public void setup(){
        lightning = new Lightning(GamePanel, 200);
    }
    public void draw(Graphics2D g2){
        lightning.draw(g2);
    }
}
