package Environment;

import java.awt.*;

public class PostProcessing
{
    Lightning lightning;
    public PostProcessing()
    {
    }

    public void setup()
    {
        lightning = new Lightning(200);
    }

    public void draw(Graphics2D g2){
        lightning.draw(g2);
    }
}
